package seven.g5;


import java.util.ArrayList;
import java.util.HashMap;

import seven.g5.Logger.LogLevel;
import seven.g5.data.ScrabbleParameters;
import seven.g5.strategies.KickOffStrategy;
import seven.g5.strategies.SevenLetterStrategy;
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

	//dictionary handler
	private DictionaryHandler dh;

	public G5_Scrabblista() {
		this.log = new Logger(LogLevel.ERROR, this.getClass());
		this.strategy = new SevenLetterStrategy();
		this.myRack = new ArrayList<Letter>();
		this.dh = new DictionaryHandler();
		this.numberLettersRemaining =  new HashMap<Character, Integer>();
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

		//get the letters we start with
		if (this.roundNum == 0) {
			this.totalRounds = PlayerList.size() * 7;
			for(Letter ltr : secretstate.getSecretLetters()) {
				this.myRack.add(new Letter(ltr.getAlphabet(),ScrabbleParameters.getScore(ltr.getAlphabet())));
			}
		}

		//get results from last round
		if(PlayerBidList != null && PlayerBidList.size() > 0 ) {
			PlayerBids currentPlayerBids = (PlayerBids)(PlayerBidList.get(PlayerBidList.size()-1));
			if( (currentPlayerBids.getWonBy().equals("seven.g5.G5_Scrabblista"))){
				this.totalPoints -= currentPlayerBids.getWinAmmount();
				this.myRack.add(currentPlayerBids.getTargetLetter());
			}       
		}

		//fill person info
		PlayerInfo pi = new PlayerInfo(this.myRack, PlayerID, this.dh);

		//fill gameInfo
		GameInfo gi = new GameInfo(PlayerBidList, bidLetter, this.totalRounds, secretstate);

		return strategy.getBid(gi, pi);
	}

	//this is stuff regarding probability and tiles remaining
	protected void decrementLettersRemainingInBag(Letter letter2) {
		int oldAmount = numberLettersRemaining.get(letter2.getAlphabet());
		numberLettersRemaining.put(letter2.getAlphabet(), --oldAmount );
	}

	private void initializeLettersRemaining() {
		numberLettersRemaining.put('A', ScrabbleParameters.getCount('A'));
		numberLettersRemaining.put('B', ScrabbleParameters.getCount('B'));
		numberLettersRemaining.put('C', ScrabbleParameters.getCount('C'));
		numberLettersRemaining.put('D', ScrabbleParameters.getCount('D'));
		numberLettersRemaining.put('E', ScrabbleParameters.getCount('E'));
		numberLettersRemaining.put('F', ScrabbleParameters.getCount('F'));
		numberLettersRemaining.put('G', ScrabbleParameters.getCount('G'));
		numberLettersRemaining.put('H', ScrabbleParameters.getCount('H'));
		numberLettersRemaining.put('I', ScrabbleParameters.getCount('I'));
		numberLettersRemaining.put('J', ScrabbleParameters.getCount('J'));
		numberLettersRemaining.put('K', ScrabbleParameters.getCount('K'));
		numberLettersRemaining.put('L', ScrabbleParameters.getCount('L'));
		numberLettersRemaining.put('M', ScrabbleParameters.getCount('M'));
		numberLettersRemaining.put('N', ScrabbleParameters.getCount('N'));
		numberLettersRemaining.put('O', ScrabbleParameters.getCount('O'));
		numberLettersRemaining.put('P', ScrabbleParameters.getCount('P'));
		numberLettersRemaining.put('Q', ScrabbleParameters.getCount('Q'));
		numberLettersRemaining.put('R', ScrabbleParameters.getCount('R'));
		numberLettersRemaining.put('S', ScrabbleParameters.getCount('S'));
		numberLettersRemaining.put('T', ScrabbleParameters.getCount('T'));
		numberLettersRemaining.put('U', ScrabbleParameters.getCount('U'));
		numberLettersRemaining.put('V', ScrabbleParameters.getCount('V'));
		numberLettersRemaining.put('W', ScrabbleParameters.getCount('W'));
		numberLettersRemaining.put('X', ScrabbleParameters.getCount('X'));
		numberLettersRemaining.put('Y', ScrabbleParameters.getCount('Y'));
		numberLettersRemaining.put('Z', ScrabbleParameters.getCount('Z'));
	}
}
