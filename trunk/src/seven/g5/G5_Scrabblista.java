package seven.g5;


import java.util.ArrayList;
import java.util.HashMap;

import seven.g5.Logger.LogLevel;
import seven.g5.data.ScrabbleParameters;
import seven.g5.strategies.KickOffStrategy;
import seven.g5.strategies.Strategy;
import seven.ui.Letter;
import seven.ui.Player;
import seven.ui.PlayerBids;
import seven.ui.SecretState;
import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;

public class G5_Scrabblista implements Player {

	private Logger log;
	private Strategy strategy;

	//this is out rack of letters or our "hand"
	private ArrayList<Letter> myRack;

	//round information
	private int roundNum = 0;
	private int totalRounds;

	//bidding info
	private int totalPoints = 100;
	private HashMap<Character, Integer> numberLettersRemaining;
	private int noOfTurnsRemaining;
	//dictionary handler
	private DictionaryHandler dh;
	
	
	public G5_Scrabblista() {
		this.log = new Logger(LogLevel.ERROR, this.getClass());
		this.strategy = new KickOffStrategy();
		this.myRack = new ArrayList<Letter>();
		this.dh = new DictionaryHandler();
		this.setNumberLettersRemaining(new HashMap<Character, Integer>());
		initializeLettersRemaining();
	}

	public void Register() {

		//throw new UnsupportedOperationException("Not supported yet.");
	}

	public String returnWord() {
		//throw new UnsupportedOperationException("Not supported yet.");
		return strategy.getFinalWord();
	}

	@Override
	public int Bid(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList, int totalRounds, ArrayList<String> PlayerList, SecretState secretstate, int PlayerID) {
		this.roundNum++;
		decrementLettersRemainingInBag(bidLetter); 
		if(PlayerBidList.get(PlayerBidList.size()-1).getWonBy().equals(null)){
			setNoOfTurnsRemaining(getNoOfTurns(PlayerList));
		}	
		//get the letters we start with
		if (this.roundNum == 0) {
			this.totalRounds = PlayerList.size() * 7;
			for(Letter ltr : secretstate.getSecretLetters()) {
				this.myRack.add(new Letter(ltr.getAlphabet(),ScrabbleParameters.getScore(ltr.getAlphabet())));
			}
		}
		
		//fill person info
		PlayerInfo pi = new PlayerInfo(this.myRack, PlayerID, this.dh);

		//fill gameInfo
		GameInfo gi = new GameInfo(PlayerBidList, bidLetter, this.totalRounds, secretstate, PlayerList, noOfTurnsRemaining, numberLettersRemaining);
		
		//get results from last round
		if(PlayerBidList != null && PlayerBidList.size() > 0 ) {
			PlayerBids currentPlayerBids = (PlayerBids)(PlayerBidList.get(PlayerBidList.size()-1));
			if( currentPlayerBids.getWinnerID() == pi.getPlayerId()){
				this.totalPoints -= currentPlayerBids.getWinAmmount();
				this.myRack.add(currentPlayerBids.getTargetLetter());
			}			
		}
		
		return strategy.getBid(gi, pi);
	}

	//this is stuff regarding probability and tiles remaining
	protected void decrementLettersRemainingInBag(Letter letter2) {
		int oldAmount = getNumberLettersRemaining().get(letter2.getAlphabet());
		getNumberLettersRemaining().put(letter2.getAlphabet(), --oldAmount );
		setNoOfTurnsRemaining(getNoOfTurnsRemaining() - 1);
	}

	private void initializeLettersRemaining() {
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
	public int getNoOfTurns(ArrayList<String> PlayerList){
		int noOfPlayers = PlayerList.size();
		return noOfPlayers*7;
	} 
	
	public int getProbabilityOfLetter(Letter letter1)	
	{
		return getNumberLettersRemaining().get(letter1.getAlphabet())/getNoOfTurnsRemaining();		
	}

	public void setNumberLettersRemaining(HashMap<Character, Integer> numberLettersRemaining) {
		this.numberLettersRemaining = numberLettersRemaining;
	}

	public HashMap<Character, Integer> getNumberLettersRemaining() {
		return numberLettersRemaining;
	}

	public void setNoOfTurnsRemaining(int noOfTurnsRemaining) {
		this.noOfTurnsRemaining = noOfTurnsRemaining;
	}

	public int getNoOfTurnsRemaining() {
		return noOfTurnsRemaining;
	}

}
