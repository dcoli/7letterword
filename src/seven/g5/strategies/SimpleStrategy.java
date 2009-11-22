package seven.g5.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import seven.g5.Logger;
import seven.g5.Logger.LogLevel;
import seven.g5.data.OurLetter;
import seven.g5.data.Word;
import seven.ui.Letter;
import seven.ui.PlayerBids;

public class SimpleStrategy extends Strategy {

	private OurLetter currentLetter;
	private ArrayList<OurLetter> hand = new ArrayList<OurLetter>();
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
			int totalRounds, ArrayList<String> playerList) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @param hand the arraylist of characters currently in our hand
	 * @return optimal word
	 */
	private String getOptimalWordFromHand( ArrayList<OurLetter> hand1 ) {
		return getBestWordOfList(getListOfFoundWords( hand1 ));
	}
	
	private String getOptimalWordFromFuture( ArrayList<OurLetter> hand1 ) {
		//here add every possible permutation of characters to fill out the rest of the hand
		//then we'll see what words we can create with those
		//then calculate the percentage chance of getting those letters (each Word's weightedScore)
		return null;
	}
	
	private String getBestWordOfList( ArrayList<Word> listOfFoundWords ) {
		// TODO Auto-generated method stub
		binHeapOfCurrentWords.clear();
		for( Word w: listOfFoundWords ) binHeapOfCurrentWords.add(w);
		return binHeapOfCurrentWords.peek().toString();
	}

	private ArrayList<Word> getListOfFoundWords( ArrayList<OurLetter> hand2 ) {
		// TODO Auto-generated method stub
		//this is what will call the anagram solver
		return null;
	}

	/**
	 * 
	 * @return final word to return at the end of the round 
	 */
	public String getFinalWord() {
		// TODO Auto-generated method stub
		return "yee-ha";
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


	public static void main( String[] args ) {
		SimpleStrategy strat = new SimpleStrategy();
		ArrayList<OurLetter> myHand = new ArrayList<OurLetter>();

		//ArrayList<String> myWordList = new ArrayList<String>();		
		strat.binHeapOfCurrentWords.add(new Word("CAT"));
		strat.binHeapOfCurrentWords.add(new Word("RAT"));
		strat.binHeapOfCurrentWords.add(new Word("HAT"));
				
		while( strat.binHeapOfCurrentWords.size() > 0 ) {
			System.out.println("word "+strat.binHeapOfCurrentWords.peek()+" is "+((Word)strat.binHeapOfCurrentWords.poll()).getScore() );
		}
	}
}
