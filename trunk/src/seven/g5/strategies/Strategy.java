package seven.g5.strategies;

import java.util.ArrayList;
import java.util.HashMap;

import seven.g5.data.ScrabbleParameters;
import seven.ui.Letter;
import seven.ui.PlayerBids;

public abstract class Strategy {
	
	protected HashMap<Character, Integer> numberLettersRemaining = new HashMap<Character, Integer>();
	
	public Strategy() {
		initializeLettersRemaining();
	}

	private void initializeLettersRemaining() {
		numberLettersRemaining.put('a', ScrabbleParameters.getCount('a'));
		numberLettersRemaining.put('b', ScrabbleParameters.getCount('b'));
		numberLettersRemaining.put('c', ScrabbleParameters.getCount('c'));
		numberLettersRemaining.put('d', ScrabbleParameters.getCount('d'));
		numberLettersRemaining.put('e', ScrabbleParameters.getCount('e'));
		numberLettersRemaining.put('f', ScrabbleParameters.getCount('f'));
		numberLettersRemaining.put('g', ScrabbleParameters.getCount('g'));
		numberLettersRemaining.put('h', ScrabbleParameters.getCount('h'));
		numberLettersRemaining.put('i', ScrabbleParameters.getCount('i'));
		numberLettersRemaining.put('j', ScrabbleParameters.getCount('j'));
		numberLettersRemaining.put('k', ScrabbleParameters.getCount('k'));
		numberLettersRemaining.put('l', ScrabbleParameters.getCount('l'));
		numberLettersRemaining.put('m', ScrabbleParameters.getCount('m'));
		numberLettersRemaining.put('n', ScrabbleParameters.getCount('n'));
		numberLettersRemaining.put('o', ScrabbleParameters.getCount('o'));
		numberLettersRemaining.put('p', ScrabbleParameters.getCount('p'));
		numberLettersRemaining.put('q', ScrabbleParameters.getCount('q'));
		numberLettersRemaining.put('r', ScrabbleParameters.getCount('r'));
		numberLettersRemaining.put('s', ScrabbleParameters.getCount('s'));
		numberLettersRemaining.put('t', ScrabbleParameters.getCount('t'));
		numberLettersRemaining.put('u', ScrabbleParameters.getCount('u'));
		numberLettersRemaining.put('v', ScrabbleParameters.getCount('v'));
		numberLettersRemaining.put('w', ScrabbleParameters.getCount('w'));
		numberLettersRemaining.put('x', ScrabbleParameters.getCount('x'));
		numberLettersRemaining.put('y', ScrabbleParameters.getCount('y'));
		numberLettersRemaining.put('z', ScrabbleParameters.getCount('z'));
	}

	public abstract int getBid(Letter bidLetter, ArrayList<PlayerBids> playerBidList,
			int totalRounds, ArrayList<String> playerList);

	public abstract String getFinalWord();
	
	
}
