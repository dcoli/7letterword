package seven.g5.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.io.FileReader;
import java.util.*;

import seven.ui.CSVReader;

import seven.g5.Logger;
import seven.g5.Logger.LogLevel;
import seven.g5.data.OurLetter;
import seven.g5.data.Word;
import seven.ui.Letter;
import seven.ui.PlayerBids;
import seven.ui.SecretState;

public class SimpleStrategy extends Strategy {

	protected OurLetter currentLetter;
	protected ArrayList<OurLetter> hand = new ArrayList<OurLetter>();
        //monal
		private PlayerBids cpbid = null ;
        private Letter letter ;
        int size = 0;
        ArrayList<String> ListofWords = new ArrayList<String>();
        char[] words = new char[10] ;
        Dictionary sowpods = new Dictionary();
	int currentRound = 0;

	//colin
	//private OurLetter currentLetter;
	private ArrayList<Letter> hand = new ArrayList<Letter>();
	//initialized in the Strategy constructor
	//protected HashMap<Character, Integer> numberLettersRemaining = new HashMap<Character, Integer>();
	
	PriorityQueue<Word> binHeapOfCurrentWords = new PriorityQueue<Word>(1, 
			new Comparator<Word>() {
				 public int compare(Word a, Word b)
				 {
				   float scoreA = a.getScore();
				   float scoreB = b.getScore();
				   if (scoreB>scoreA)
				        return 1;
				   else if (scoreB<scoreA)
				        return -1;
				   else 
				        return 0; 
				 }
			}
	);
	
	public SimpleStrategy () {
		super();
	}
	
	/**
	 * 
	 * @return the value of the bid to place
	 */
	public int getBid(Letter bidLetter, ArrayList<PlayerBids> playerBidList,
			int totalRounds, ArrayList<String> playerList, SecretState secretstate, int playerID) {

		//get the letters we start with
		if (currentRound++ == 0) {
			this.hand = secretstate.getSecretLetters();
			for (int i = 0; i<this.hand.size(); i++) {
				decrementLettersRemaining(hand.get(i));
			}
		}

		this.letter = bidLetter;
		
		//track how many of each letter remains (as far as we can tell)
		if (this.letter != null) decrementLettersRemaining( this.letter );

		this.playerList = playerList;
		if( playerBidList != null && playerBidList.size() > 0 ) {
			PlayerBids currentPlayerBids = (PlayerBids)(playerBidList.get(playerBidList.size()-1));
			if( (currentPlayerBids.getWonBy() != null) && (currentPlayerBids.getWonBy()).equals("seven.g5.G5_Scrabblista") ) {
				getWordlist();
				this.bidpoints -= currentPlayerBids.getWinAmmount();
			}
		}

//        if(playerBidList != null && playerBidList.size() > 0)
//        {
//            String winner = playerBidList.get(playerBidList.size()-1).getWonBy();              
//                //System.out.println(winner) ;
//                if(winner!=null && winner.equals("seven.g5.G5_Scrabblista") ) 
//        }
       
        Random rand = new Random();
        int val = 4+rand.nextInt(10);
        return val;
	}
	
	protected void decrementLettersRemaining(Letter letter2) {
		int oldAmount = numberLettersRemaining.get(letter2.getAlphabet());
		numberLettersRemaining.put(letter2.getAlphabet(), --oldAmount );
	}

	/**
	 * @param hand the arraylist of characters currently in our hand
	 * @return optimal word
	 */
	protected String getOptimalWordFromHand( ArrayList<Letter> hand1 ) {
		return getBestWordOfList(getListOfFoundWords( hand1 ));
	}
	
	protected String getOptimalWordFromFuture( ArrayList<Letter> hand1 ) {
		//here add every possible permutation of characters to fill out the rest of the hand
		//then we'll see what words we can create with those
		//then calculate the percentage chance of getting those letters (each Word's weightedScore)
		return null;
	}
	
	protected String getBestWordOfList( ArrayList<Word> listOfFoundWords ) {
		// TODO Auto-generated method stub
		binHeapOfCurrentWords.clear();
		for( Word w: listOfFoundWords ) binHeapOfCurrentWords.add(w);
		if( binHeapOfCurrentWords.peek() != null ) return binHeapOfCurrentWords.peek().toString();
		else return "";
	}

	protected ArrayList<Word> getListOfFoundWords( ArrayList<Letter> hand2 ) {
		// TODO Auto-generated method stub
		//this is what will call the anagram solver
		return null;
	}

	/**
	 * 
	 * @return final word to return at the end of the round 
	 */
	public String getFinalWord() {
		getListofPossibleWords();
		ArrayList<Word> finalList = convertStringsToWords(ListofWords);
		String bestWord = getBestWordOfList( finalList );
		if ( bestWord != null ) return bestWord;
		else return "";
	}

	protected ArrayList<Word> convertStringsToWords( ArrayList<String> theList ) {
		ArrayList<Word> finalList1 = new ArrayList<Word>();
		for (int i=0; i<theList.size(); i++) {
			finalList1.add( new Word( theList.get(i)));
		}
		return finalList1;
	}
	
        public void getListofPossibleWords(){
                //getWordlist();
                try{
                        CSVReader csvreader = new CSVReader(new FileReader("sowpods.txt"));
                String[] nextLine;
                csvreader.readNext(); // Waste the first line
                while((nextLine = csvreader.readNext()) != null)
                {
                    String word = nextLine[1];
                    sowpods.wordlist.put(word, Boolean.TRUE);
                }

                        } catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println("\n Could not load dictionary!");
                }
                        solve(0);
                //return ListofWords;
        }
        void solve(int k)
        {
                if(k == size)
                {
                String s = "" ;
                for(int i=0;i<size;++i) s+= words[i] ;
                System.out.println(s) ;

                        if(sowpods.wordlist.containsKey(s))
                        {
                        	System.out.println("FOUND") ;
                                ListofWords.add(s);
                        }
                        return ;
                }
                for(int i=0;i<size;i++)
                {
                        char c = words[k] ;
                        words[k] = words[i] ;
                        words[i] = c ;

                        solve(k+1) ;

                        c = words[k] ;
                        words[k] = words[i] ;
                        words[i] = c ;
                }
        }

        public void getWordlist(){
                char c = letter.getAlphabet();
                ++size;
                words[size-1] = c;
                
        }


//	public static void main( String[] args ) {
//		SimpleStrategy strat = new SimpleStrategy();
//		ArrayList<Letter> myHand = new ArrayList<Letter>();
//		
//		myHand.add(new Letter('E', 0));
//		myHand.add(new Letter('F', 0));
//		myHand.add(new Letter('G', 0));
//		myHand.add(new Letter('H', 0));
//		
//		for(int i=0; i<myHand.size(); i++) {
//			System.out.println(strat.numberLettersRemaining.get(((Letter)myHand.get(i)).getAlphabet()));
//			strat.decrementLettersRemaining(myHand.get(i));
//			System.out.println(strat.numberLettersRemaining.get(((Letter)myHand.get(i)).getAlphabet()));
//		}
//		
//		//ArrayList<String> myWordList = new ArrayList<String>();		
////		strat.binHeapOfCurrentWords.add(new Word("CAT"));
////		strat.binHeapOfCurrentWords.add(new Word("RAT"));
////		strat.binHeapOfCurrentWords.add(new Word("HAT"));
////		
////		while( strat.binHeapOfCurrentWords.size() > 0 ) {
////			System.out.println("word "+strat.binHeapOfCurrentWords.peek()+" is "+((Word)strat.binHeapOfCurrentWords.poll()).getScore() );
////		}
//	}
	
}

class Dictionary
{
    Hashtable<String,Boolean> wordlist;

    public Dictionary()
    {
        wordlist = new Hashtable<String, Boolean>();
    }
	
	/**
	 * @param numberOfTurnsLeft the number of characters that are still to be drawn, a function of the number of players * 7 - number of characters dispensed so far
	 * @return the value of the letter times the chance of getting it.
	 */
	public float getAdjustedValueOfWord( int numberOfTurnsLeft, String targetWord ) {
//		TODO
//		float probability = (float)(numberInGame-numberSeen)/(float)numberOfTurnsLeft;
//		return (float)value * probability;
		return 0;
	}

}
