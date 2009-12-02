package seven.g5;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import seven.g5.Logger.LogLevel;
import seven.g5.apriori_ben.DataMine;
import seven.g5.apriori_ben.LetterMine;
import seven.g5.apriori_ben.DataMine.ItemSet;
import seven.g5.apriori_ben.LetterMine.LetterSet;
import seven.g5.data.Word;
import seven.ui.CSVReader;
import seven.ui.Letter;

public class DictionaryHandler {

	//a priori stuff
	private DataMine mine = null;
	private ItemSet[] answer;
	private Logger log;
	
	//pastAnagram stuff
	HashSet<String> wordlist = new HashSet<String>();


	//futureAnagram stuff
	private PriorityQueue<Word> binHeapOfWordsByValue;

	public DictionaryHandler() {
		this.binHeapOfWordsByValue = new PriorityQueue<Word>(1);
		this.mine = new LetterMine("src/seven/g5/super-small-wordlist.txt");//src/seven/g5/data/FilteredWords.txt");
		this.loadDictionary();
		
		this.mine.buildIndex();
		this.answer = mine.aPriori(0.000001);
	}
	
	public ArrayList<Word> futureAnagram(List<Letter> hand) {
		if(hand.size() == 0) {
			return new ArrayList<Word>(0);
		} else {
			ArrayList<Letter> possibleHand = new ArrayList<Letter>(hand);  
			String[] stringLetters = new String[possibleHand.size()];
			for(int i = 0; i < possibleHand.size(); i++) {
				stringLetters[i] = possibleHand.get(i).getAlphabet().toString();
			}
			LetterSet i = (LetterSet) mine.getCachedItemSet(stringLetters);
			String[] words = i.getWords();
        	return convertStringsToWords(words);
		}
	}

	public ArrayList<Word> convertStringsToWords(String[] theList) {
		ArrayList<Word> finalList1 = new ArrayList<Word>();
		for (int i=0; i<theList.length; i++) {
			finalList1.add( new Word( theList[i] ));
		}
		return finalList1;
	}

	public Word getBestWordOfList(ArrayList<Word> listofWords2) {
		// TODO Auto-generated method stub
		binHeapOfWordsByValue.clear();
		for( Word w: listofWords2 ) binHeapOfWordsByValue.add(w);
		if( binHeapOfWordsByValue.peek() != null ) return binHeapOfWordsByValue.peek();
		else return null;
	}
	
	//setup for pastAnagrams
	public void loadDictionary() {
		try{
			CSVReader csvreader = new CSVReader(new FileReader("src/seven/g5/super-small-wordlist.txt"));
			String[] nextLine;
			//csvreader.readNext(); // Waste the first line
			while((nextLine = csvreader.readNext()) != null)
			{
				String word = nextLine[0];
				this.wordlist.add(word);
			}

		} catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("\n Could not load dictionary!");
		}
	}
	
	public ArrayList<Word> pastAnagram(ArrayList<Letter> rack) {
		ArrayList<Word> goodAnagrams = new ArrayList<Word>();
		
		int[] freq = new int[26] ;
		int[] tp = new int[26] ;
		for(int i=0;i<26;++i) freq[i] = 0 ;
		for(int i=0;i<rack.size();++i) ++freq[rack.get(i).getAlphabet()-'A'] ;
		for(String str : this.wordlist) if(this.wordlist.contains(str)){

			for(int i=0;i<26;++i) tp[i] = 0 ;
			int i = 0 ;
			for(;i<str.length();++i)
			{
				char ch = str.charAt(i) ;
				++tp[ch-'A'] ;
				if(tp[ch-'A'] > freq[ch-'A']) break ;
			}
			if(i < str.length()) continue ;
			goodAnagrams.add(new Word(str)) ;
		}
		return goodAnagrams;
	}
}
