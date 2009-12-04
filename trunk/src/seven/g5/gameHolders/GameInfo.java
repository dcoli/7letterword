package seven.g5.gameHolders;

import java.util.ArrayList;
import java.util.HashMap;

import seven.g5.data.ScrabbleParameters;
import seven.g5.data.Word;
import seven.ui.Letter;
import seven.ui.PlayerBids;
import seven.ui.SecretState;
import java.util.*;
public class GameInfo {
	
	private ArrayList<PlayerBids> playerBidList;
	private Letter currentBidLetter;
	private int numRounds;
	private SecretState secretState;
	private ArrayList<String> playerList;
	private int noOfTurnsRemaining;
	private HashMap<Character, Integer> numberLettersRemaining;
	private int totalLettersRemaining;
	
	public int getTotalLettersRemaining() {
		return totalLettersRemaining;
	}

	public void setTotalLettersRemaining(int totalLettersRemaining) {
		this.totalLettersRemaining = totalLettersRemaining;
	}

	public GameInfo(){}
	
	public GameInfo(ArrayList<PlayerBids> playerBidList, Letter currentBid, int numRounds, SecretState st, ArrayList<String> PlayerList, int noOfTurnsRemaining, HashMap<Character, Integer> numberLettersRemaining) {
		this.playerBidList = playerBidList;
		this.currentBidLetter = currentBid;
		this.numRounds = numRounds;
		this.secretState = st;
		this.playerList = PlayerList;
		this.noOfTurnsRemaining = noOfTurnsRemaining;
		this.numberLettersRemaining = numberLettersRemaining;
		this.totalLettersRemaining = 98;
	}
	public ArrayList<PlayerBids> getPlayerBidList() {
		return playerBidList;
	}
	public void setPlayerBidList(ArrayList<PlayerBids> playerBidList) {
		this.playerBidList = playerBidList;
	}
	public Letter getCurrentBidLetter() {
		return currentBidLetter;
	}
	public void setCurrentBidLetter(Letter currentBid) {
		this.currentBidLetter = currentBid;
	}
	public int getNumRounds() {
		return numRounds;
	}
	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}
	public SecretState getSecretState() {
		return secretState;
	}
	public void setSecretState(SecretState secretState) {
		this.secretState = secretState;
	}
	public ArrayList<String> getPlayerList() {
		return playerList;
	}
	public HashMap<Character, Integer> getNumberLettersRemaining() {
		return numberLettersRemaining;
	}
	public int getNoOfTurnsRemaining() {
		return noOfTurnsRemaining;
	}

	//this is stuff regarding probability and tiles remaining
	public void decrementLettersRemainingInBag(Letter letter2) {
		int oldAmount = getNumberLettersRemaining().get(letter2.getAlphabet());
		getNumberLettersRemaining().put(letter2.getAlphabet(), --oldAmount );
		--totalLettersRemaining;
		--noOfTurnsRemaining;
	}

	public void initializeLettersRemaining() {
		getNumberLettersRemaining().put('A', ScrabbleParameters.getCount('A'));
		getNumberLettersRemaining().put('B', ScrabbleParameters.getCount('B'));
		getNumberLettersRemaining().put('C', ScrabbleParameters.getCount('C'));
		getNumberLettersRemaining().put('D', ScrabbleParameters.getCount('D'));
		getNumberLettersRemaining().put('E', ScrabbleParameters.getCount('E'));
		getNumberLettersRemaining().put('F', ScrabbleParameters.getCount('F'));
		getNumberLettersRemaining().put('G', ScrabbleParameters.getCount('G'));
		getNumberLettersRemaining().put('H', ScrabbleParameters.getCount('H'));
		getNumberLettersRemaining().put('I', ScrabbleParameters.getCount('I'));
		getNumberLettersRemaining().put('J', ScrabbleParameters.getCount('J'));
		getNumberLettersRemaining().put('K', ScrabbleParameters.getCount('K'));
		getNumberLettersRemaining().put('L', ScrabbleParameters.getCount('L'));
		getNumberLettersRemaining().put('M', ScrabbleParameters.getCount('M'));
		getNumberLettersRemaining().put('N', ScrabbleParameters.getCount('N'));
		getNumberLettersRemaining().put('O', ScrabbleParameters.getCount('O'));
		getNumberLettersRemaining().put('P', ScrabbleParameters.getCount('P'));
		getNumberLettersRemaining().put('Q', ScrabbleParameters.getCount('Q'));
		getNumberLettersRemaining().put('R', ScrabbleParameters.getCount('R'));
		getNumberLettersRemaining().put('S', ScrabbleParameters.getCount('S'));
		getNumberLettersRemaining().put('T', ScrabbleParameters.getCount('T'));
		getNumberLettersRemaining().put('U', ScrabbleParameters.getCount('U'));
		getNumberLettersRemaining().put('V', ScrabbleParameters.getCount('V'));
		getNumberLettersRemaining().put('W', ScrabbleParameters.getCount('W'));
		getNumberLettersRemaining().put('X', ScrabbleParameters.getCount('X'));
		getNumberLettersRemaining().put('Y', ScrabbleParameters.getCount('Y'));
		getNumberLettersRemaining().put('Z', ScrabbleParameters.getCount('Z'));
	}
}