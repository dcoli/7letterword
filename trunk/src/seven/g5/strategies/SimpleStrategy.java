package seven.g5.strategies;

import java.util.ArrayList;
import java.util.HashMap;

import seven.g5.data.OurLetter;
import seven.g5.data.Word;
import seven.ui.Letter;
import seven.ui.PlayerBids;

public class SimpleStrategy extends Strategy {

	private OurLetter currentLetter;
	private ArrayList<OurLetter> hand = new ArrayList<OurLetter>();
	//initialized in the Strategy constructor
	//protected HashMap<Character, Integer> numberLettersRemaining = new HashMap<Character, Integer>();
	
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
	
	private String getBestWordOfList( ArrayList<Word> listOfFoundWords ) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Word> getListOfFoundWords( ArrayList<OurLetter> hand2 ) {
		// TODO Auto-generated method stub
		//this is what will call janagram
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

}
