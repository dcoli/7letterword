package seven.g5;

import java.util.ArrayList;

import seven.g5.Logger.LogLevel;
import seven.g5.strategies.SimpleStrategy;
import seven.g5.strategies.Strategy;
import seven.ui.Letter;
import seven.ui.Player;
import seven.ui.PlayerBids;
import seven.ui.SecretState;

public class G5_Scrabblista implements Player {

	private Logger log;
	private Strategy strategy;
	
	public G5_Scrabblista( ) {
		log = new Logger(LogLevel.DEBUG, this.getClass());
		strategy = new SimpleStrategy();
	}
	
	public void Register() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

   public String returnWord() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return strategy.getFinalWord();
    }

	@Override
	public int Bid(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList,
			int totalRounds, ArrayList<String> PlayerList,
			SecretState secretstate, int PlayerID) {
		// TODO Auto-generated method stub
        return strategy.getBid(bidLetter, PlayerBidList, totalRounds, PlayerList, secretstate, PlayerID);
	}

}
