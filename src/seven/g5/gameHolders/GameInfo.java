package seven.g5.gameHolders;

import java.util.ArrayList;

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
	
	public GameInfo(){}
	
	public GameInfo(ArrayList<PlayerBids> playerBidList, Letter currentBid, int numRounds, SecretState st, ArrayList<String> PlayerList, int noOfTurnsRemaining, HashMap<Character, Integer> numberLettersRemaining) {
		this.playerBidList = playerBidList;
		this.currentBidLetter = currentBid;
		this.numRounds = numRounds;
		this.secretState = st;
		this.playerList = PlayerList;
		this.noOfTurnsRemaining = noOfTurnsRemaining;
		this.numberLettersRemaining = numberLettersRemaining;
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
}