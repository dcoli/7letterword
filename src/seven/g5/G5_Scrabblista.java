package seven.g5;

import java.util.ArrayList;

import seven.g5.strategies.SimpleStrategy;
import seven.g5.strategies.Strategy;
import seven.ui.Letter;
import seven.ui.PlayerBids;

public class G5_Scrabblista {

	private Strategy strategy;
	
	public G5_Scrabblista( ) {
		strategy = new SimpleStrategy();
	}
	
	public void Register() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public int Bid(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList, int total_rounds,ArrayList<String> PlayerList) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return strategy.getBid(bidLetter, PlayerBidList, total_rounds, PlayerList);
    }

    public String returnWord() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return strategy.getFinalWord();
    }

}
