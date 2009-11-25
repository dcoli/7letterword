package seven.g2.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import seven.ui.Scrabble;

/**
 *
 */
public class WordList {
	private HashMap<String, LetterGroup> occurrences_cache = new HashMap<String, LetterGroup>();
//	public Scrabble s = new Scrabble();
	private Logger log = new Logger(Logger.LogLevel.NONE,this.getClass());

	
	public LetterGroup getLetterGroup(String w)
	{
		if(occurrences_cache.containsKey(w))
			return occurrences_cache.get(w);
		Statement s;
		try {
			s = conn.createStatement();
			w = indexValue(w);
			ResultSet rs = s.executeQuery("SELECT w.word,l.letters,w.score,oc_1,oc_2,oc_3,oc_4,oc_5,oc_6,oc_7 FROM letterCollections" +
					" as l left join words as w on w.letters=l.letters WHERE l.letters='"+w+"';");
			LetterGroup n = new LetterGroup(w);
			if(rs.next())
			{
				n.setAnagram(rs.getString("word"));
				n.setScore(rs.getInt("score"));
				for(int i=0;i<7;i++)
				{
					n.occurrences[i] = rs.getInt("oc_"+(i+1));
				}
			}
			occurrences_cache.put(w, n);
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return occurrences_cache.get(w);
	}
	public static void main(String[] args) {
		WordList l = new WordList();
		ArrayList<ScrabbleWord> words = l.getValidWords("apple");
		System.out.println(words);

		HashMap<String, String> successors = l.getSuccessors("JAN");
		for (String s : successors.keySet()) {
			words = l.getValidWords(successors.get(s));
			if (words.size() > 0)
				System.out.println(s + ":" + words);
		}
		
		LetterGroup let = l.getLetterGroup("LETTERS");
		System.out.println(let.getBestSubstring());
		
		let = l.getLetterGroup("JEAN");
		System.out.println(let.getBestSubstring());
//		System.out.println(l.getLetterGroup("JAN").getSuccessor("A"));
//		long start = System.currentTimeMillis();
//		System.out.println(l.getLetterGroup("ab").getAllSuccessors());
//		System.out.println("Op took " + (System.currentTimeMillis() - start));
//		 start = System.currentTimeMillis();
//		System.out.println(l.getLetterGroup("abc").getAllSuccessors());
//		System.out.println("Op took " + (System.currentTimeMillis() - start));
//		start = System.currentTimeMillis();
//		System.out.println(l.getLetterGroup("abcd").getAllSuccessors());
//		System.out.println("Op took " + (System.currentTimeMillis() - start));
		l.disconnect();
	}

	public WordList() {
		System.out.println("Start loading");
//		loadWords();
		initDB();
		System.out.println("Done loading");
	}
	public void disconnect()
	{
		if (conn != null)
        {
            try
            {
                conn.close ();
                System.out.println ("Database connection terminated");
            }
            catch (Exception e) { /* ignore close errors */ }
        }
	}
	Connection conn = null;
	private void initDB()
	{
		

        try
        {
            String url = "jdbc:sqlite:src/seven/g2/util/seven.db";
            Class.forName ("org.sqlite.JDBC").newInstance ();
            conn = DriverManager.getConnection (url);
//        	String url = "jdbc:mysql://projects.seas.columbia.edu/4444p4";
//        	String userName = "ppsf09";
//            String password = "ppsf09";
//            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
//            conn = DriverManager.getConnection (url, userName, password);

            System.out.println ("Database connection established");
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
        }

	}
	/**
	 * For the letters in a string, find all of the index values that succeed
	 * that string for one depth, indexed in a hashmap by the new letter added
	 *  EX: getSuccessors("AC") -> a->AAC, b->ABC, d->ACD, ....
	 * 
	 * @param l
	 *            The string
	 * @return
	 */
	public HashMap<String, String> getSuccessors(String l) {
		String t = indexValue(l);
		return getLetterGroup(t).getSuccessors();
	}
	
	/**
	 * For the letters in a string, find ALL the index values that succeed that string
	 * for all depths until we get to length 7, simply in a set
	 * 
	 * @param l
	 * @return
	 */
	public ArrayList<HashSet<LetterGroup>> getAllSuccessors(String l)
	{
		String t = indexValue(l);
		return getLetterGroup(t).getAllSuccessors();
	}
	/**
	 * Take a string with a list of letters, turn it into words
	 * 
	 * @param letters
	 * @return
	 */
	public ArrayList<ScrabbleWord> getValidWords(String letters) {
		letters = letters.toUpperCase();
		HashSet<String> potentialWords = shuffleString(letters, 0,
				new HashSet<String>());
		ArrayList<ScrabbleWord> ret = new ArrayList<ScrabbleWord>();
		for (String l : potentialWords) {
			int points = ScrabbleUtility.getScrabbleWordScore(l);
			if (points > 0) {
				ScrabbleWord w = new ScrabbleWord();
				w.score = points;
				w.word = l;
				ret.add(w);
			}
		}
		Collections.sort(ret);
		return ret;
	}

	private HashSet<String> shuffleString(String s, int l, HashSet<String> ret) {
		if (l == s.length()) {
			ret.add(s);
			return ret;
		} else {
			char[] sa = s.toCharArray();
			for (int i = l; i < s.length(); i++) {
				char c = sa[l];
				sa[l] = sa[i];
				sa[i] = c;
				s = String.valueOf(sa);
				ret = shuffleString(s, l + 1, ret);
				c = sa[l];
				sa[l] = sa[i];
				sa[i] = c;
				s = String.valueOf(sa);
			}
		}
		return ret;
	}
	public static String indexValue(String word)
	{
		ArrayList<String> letters = new ArrayList<String>();
		for (int i = 0; i < word.length(); i++) {
			letters.add(word.substring(i, i + 1));
		}
		Collections.sort(letters);
		String sorted = "";
		for (int j = 0; j < word.length(); j++) {
			sorted += letters.get(j);
		}
		sorted = sorted.toUpperCase();
		return sorted;
	}

	public int[] getNumWords(String word) {
		String l = indexValue(word);
		LetterGroup lg = getLetterGroup(l);
		return lg.occurrences;
	}

	private void loadWords() {
//		try {
//			Scanner s = new Scanner(new File("src/seven/g2/util/WordIndex.txt"));
//			while (s.hasNextLine()) {
//				String[] line = s.nextLine().split(",");
//				LetterGroup l = new LetterGroup(line[0]);
//				l.parseOccurrences(line, 1);
//				occurrences.put(l.letters, l);
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	

	public class LetterGroup {
		String letters ="";
		int[] occurrences;
		int[] meanScores;
		String anagram = "";
		int score=0;
		public String getLetters() {
			return letters;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public void setAnagram(String anagram) {
			this.anagram = anagram;
		}
		public String getAnagram() {
			return anagram;
		}
		public String getWord() {
			return anagram;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[Letters: " + letters + ", Anagram: " + anagram + ", score: " + score + "]";
		}
		/**
		 * @return the occurrences
		 */
		public int[] getOccurrences() {
			return occurrences;
		}

		public LetterGroup(String letters) {
			this.letters = letters;
			occurrences = new int[7];
			meanScores = new int[7];
		}

		public LetterGroup() {
			// TODO Auto-generated constructor stub
		}
		public void parseOccurrences(String[] a, int offset) {
			int j = letters.length()-1;
			for(int i=0;i<occurrences.length;i++)
			{
				occurrences[i] = 0;
			}
			for (int i = 1; i < a.length; i++) {
				occurrences[j] = Integer.parseInt(a[i]);
//				meanScores[j] = Integer.parseInt(a[i + 1]);
				j++;
			}
		}
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return letters.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return letters.equals(((LetterGroup) obj).letters);
		}
		
		public LetterGroup getBestSubstring()
		{
			
			String query = "SELECT w.word,w.score," +
					"l.letters,oc_1,oc_2,oc_3,oc_4,oc_5,oc_6,oc_7" +
			" FROM lettercollections l inner join words w on w.letters=l.letters " +
			" WHERE ";
			int counts[] = new int[26];
			for(int i = 0;i<26;i++)
				counts[i] = 0;
			for(int i =0;i<letters.length();i++)
				counts[letters.charAt(i) - 'A']++;
			for(int i=0;i<26;i++)
			{
//				if(counts[i] > 0)
//				{
					query += String.valueOf((char) (i + 'A')) + "<=" + counts[i];
					query += " and ";
//				}
			}
			query = query.substring(0,query.length()-5) + " order by score desc limit 1;";
//			System.out.println(query);
		//	System.out.println(query);
			try {
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(query);
				if(rs.next())
				{
					if(occurrences_cache.containsKey(rs.getObject("letters")))
						return occurrences_cache.get(rs.getString("letters"));
					else
					{
						LetterGroup l = new LetterGroup(rs.getString("letters"));
						l.setScore(rs.getInt("score"));
						l.setAnagram(rs.getString("word"));
						for(int i=0;i<7;i++)
						{
							l.occurrences[i] = rs.getInt("oc_"+(i+1));
						}
						return l;
					}
				}
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("ERROR - Could not even find a substring!!!");
			
			return new LetterGroup();
		}
		public LetterGroup getBestSuccessorWith(int[] lettersLeft)
		{
			long start = System.currentTimeMillis();
			String query = "SELECT w.word,w.score," +
			"l.letters,oc_1,oc_2,oc_3,oc_4,oc_5,oc_6,oc_7" +
			" FROM lettercollections l inner join words w on w.letters=l.letters " +
			" WHERE ";
			int counts[] = new int[26];
			for(int i = 0;i<26;i++)
				counts[i] = 0;
			for(int i =0;i<letters.length();i++)
				counts[letters.charAt(i) - 'A']++;
			String q2 = "(";
			for(int i=0;i<26;i++)
			{
				if(counts[i] > 0)
				{
					query += String.valueOf((char) (i + 'A')) + ">=" + counts[i];
					query += " and ";
				}
				query += String.valueOf((char) (i + 'A')) + "<=" + (lettersLeft[i] + counts[i]);
				query += " and ";
				q2 += "abs("+String.valueOf((char) (i + 'A'))+" - " + counts[i] + ")";
				if(i!= 25)
					q2+=" + ";
			}
			q2 += ") <= 7";
			query = query.substring(0,query.length()-5) + " and "+ q2 + " order by score desc limit 1;";
			System.out.println(query);
		//	System.out.println(query);
			try {
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(query);
				if(rs.next())
				{
					if(occurrences_cache.containsKey(rs.getObject("letters")))
					{
						log.info("GetBestSuccessorWith: " + (System.currentTimeMillis() - start));
						log.info("GetBestSuccessorWith: " + query);
						return occurrences_cache.get(rs.getString("letters"));
					}
					else
					{
						LetterGroup l = new LetterGroup(rs.getString("letters"));
						l.setScore(rs.getInt("score"));
						l.setAnagram(rs.getString("word"));
						for(int i=0;i<7;i++)
						{
							l.occurrences[i] = rs.getInt("oc_"+(i+1));
						}
						log.info("GetBestSuccessorWith: " + (System.currentTimeMillis() - start));
						log.info("GetBestSuccessorWith: " + query);
						return l;
					}
				}
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("GetBestSuccessorWith: " + (System.currentTimeMillis() - start));
			log.info("GetBestSuccessorWith: " + query);
			
			//If we're still here, then we didn't find a good successor. Instead, return the best substring
			log.debug("Couldn't form a word with all of our letters, " + letters + " (" + letters.length() + ")");
			return getBestSubstring();
		}
		private ArrayList<HashSet<LetterGroup>> successors = new ArrayList<HashSet<LetterGroup>>();
		private void generateSuccesors()
		{
			for(int i =0;i<7;i++)
			{
				successors.add(new HashSet<LetterGroup>());
			}
			if(letters.length() < 7)
			{
				String query = "SELECT l.letters,oc_1,oc_2,oc_3,oc_4,oc_5,oc_6,oc_7,word" +
						" FROM lettercollections l left join words w on w.letters=l.letters " +
						" WHERE ";
				int counts[] = new int[26];
				for(int i = 0;i<26;i++)
					counts[i] = 0;
				for(int i =0;i<letters.length();i++)
					counts[letters.charAt(i) - 'A']++;
				for(int i=0;i<26;i++)
				{
					if(counts[i] > 0)
					{
						query += String.valueOf((char) (i + 'A')) + ">=" + counts[i];
						query += " and ";
					}
				}
				query = query.substring(0,query.length()-5) + ";";
//				System.out.println(query);
				try {
					Statement s = conn.createStatement();
					ResultSet rs = s.executeQuery(query);
					while(rs.next())
					{
						if(!rs.getString("letters").equals(letters))
						{
							if(occurrences_cache.containsKey(rs.getString("letters")))
								successors.get(rs.getString("letters").length()-1).add(occurrences_cache.get(rs.getString("letters")));
							else
							{
								LetterGroup n = new LetterGroup(rs.getString("letters"));
								n.setAnagram(rs.getString("word"));
								for(int i=0;i<7;i++)
								{
									n.occurrences[i] = rs.getInt("oc_"+(i+1));
								}
								successors.get(rs.getString("letters").length() -1).add(n);
							}
						}
					}
					s.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		public ArrayList<HashSet<LetterGroup>> getAllSuccessors()
		{
			if(successors.size() == 0)
				generateSuccesors();
			return successors;
		}
		public HashMap<String, String> getSuccessors() {
			HashMap<String, String> ret = new HashMap<String, String>();
			for (String l : WordList.letters) {
				String t = letters + l;
				ret.put(l, indexValue(t));
			}
			return ret;
		}
		public LetterGroup getSuccessor(String nextLetter)
		{
			String l = letters + nextLetter;
			l = indexValue(l);
			return getLetterGroup(l);
		}
		public ArrayList<HashSet<LetterGroup>> getAllSuccessors(String nextLetter)
		{
			String l = letters + nextLetter;
			l = indexValue(l);
			return getLetterGroup(l).getAllSuccessors();
		}
	}

	public static final String[] letters = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };
}
