package seven.g3.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import seven.g3.KnowledgeBase.KnowledgeBase;
import seven.g3.KnowledgeBase.Word;
import seven.ui.Letter;
import seven.ui.PlayerBids;
import seven.g3.ScrabbleValues;
import seven.ui.SecretState;

public class NaiveStrategy extends Strategy {
	
	public NaiveStrategy(KnowledgeBase kb, int totalRounds, ArrayList<String> playerList, HashMap<Character, Integer> letters)
	{
		super(kb, totalRounds, playerList, letters);
	}
	
	@Override
	public void update(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList,
			SecretState secretstate) {
		// TODO Auto-generated method stub

	}

	@Override
	public int calculateBidAmount(Letter bidLetter) {
		//Naive method. Always bid letter amount.
		return ScrabbleValues.letterScore(bidLetter.getAlphabet());
	}

	@Override
	public int calculateOthersLetterWorth(Letter bidLetter, int playerID) {
		//Naive method. Always bid letter amount.
		return ScrabbleValues.letterScore(bidLetter.getAlphabet());
	}

	@Override
	public int calculatePersonalLetterWorth(Letter bidLetter) {
		//Naive method. Always bid letter amount.
		return ScrabbleValues.letterScore(bidLetter.getAlphabet());
	}

	@Override
	public PriorityQueue<Word> findPossibleWords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriorityQueue<Word> findPossibleWords(Letter letter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriorityQueue<Word> findPossibleWordsOther(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriorityQueue<Word> findPossibleWordsOther(int playerID, Letter letter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
