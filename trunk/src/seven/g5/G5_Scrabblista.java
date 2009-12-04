package seven.g5;


import java.util.ArrayList;
import java.util.HashMap;

import seven.g5.Logger.LogLevel;
import seven.g5.data.ScrabbleParameters;
import seven.g5.strategies.EmpiricalFrameworkStrategy;
import seven.g5.strategies.RareLetterKickOffStrategy;
import seven.g5.strategies.SevenLetterManyPlayerStrategy;
import seven.g5.strategies.CommonLetterKickOffStrategy;
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
	private int turnNumber = 0;
	private int totalRounds;

	//bidding info
	private int totalPoints = 100;
	private HashMap<Character, Integer> numberLettersRemaining = new HashMap<Character, Integer>();
	private int numberTurnsRemaining;
	
	//dictionary handler
	private DictionaryHandler dh;
	
	//info holder
	PlayerInfo pi;
	GameInfo gi;
	
	
	public G5_Scrabblista() {
		this.log = new Logger(LogLevel.DEBUG, this.getClass());
		this.myRack = new ArrayList<Letter>();
		this.dh = new DictionaryHandler();
	}

	public void Register() {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	public String returnWord() {
		String finalWord = strategy.getFinalWord(this.gi, this.pi);
		this.turnNumber = 1;
		this.myRack.clear();
		this.pi.setRack(myRack);
		this.gi.setPlayerBidList(null);
		this.gi.setCurrentBidLetter(null);
		this.gi.initializeLettersRemaining();
		return finalWord;
	}

	@Override
	public int Bid(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList, int totalRounds, ArrayList<String> PlayerList, SecretState secretstate, int PlayerID) {
				//get the letters we start with
		
		if(this.turnNumber == 1) {
			numberTurnsRemaining =  PlayerList.size() * 7;
			for(Letter ltr : secretstate.getSecretLetters()) {
				this.myRack.add(new Letter(ltr.getAlphabet(),ScrabbleParameters.getScore(ltr.getAlphabet())));
			}
		}
		
		if( PlayerList.size() > 4 ) {
			if( this.myRack.size() == 0 ) 
				this.strategy = new CommonLetterKickOffStrategy();
			else
				this.strategy = new SevenLetterManyPlayerStrategy();
		}
		else {
			if( this.myRack.size() == 0 )
				this.strategy = new RareLetterKickOffStrategy();
			else if( this.myRack.size() == 1 )
				this.strategy = new CommonLetterKickOffStrategy();
			else this.strategy = new EmpiricalFrameworkStrategy();
		}

		//fill person info
		this.pi = new PlayerInfo(this.myRack, PlayerID, this.dh);

		//fill gameInfo
		this.gi = new GameInfo(PlayerBidList, bidLetter, this.totalRounds, secretstate, PlayerList, numberTurnsRemaining, numberLettersRemaining);
		
		//get results from last round
		if(PlayerBidList != null && PlayerBidList.size() > 0 ) {
			PlayerBids currentPlayerBids = (PlayerBids)(PlayerBidList.get(PlayerBidList.size()-1));
			if( currentPlayerBids.getWinnerID() == this.pi.getPlayerId()){
				this.totalPoints -= currentPlayerBids.getWinAmmount();
				this.myRack.add(currentPlayerBids.getTargetLetter());
			}			
		}
		
//		if(pi.getRack().size() >= 2) {
//			this.strategy = new EmpiricalFrameworkStrategy();
//		}

		if(this.turnNumber == 0) {
			this.gi.initializeLettersRemaining();
		}
		
		turnNumber++;
		
		return strategy.getBid(this.gi, this.pi);
	}
}
