package seven.g5.strategies;

import java.util.ArrayList;

import seven.g5.data.Word;
import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;

public class SevenLetterStrategy extends Strategy {

	@Override
	public String getFinalWord( ) {
    	//String[] wordPathsArray = useAPriori( rack, gi.getCurrentBid() );
		return "SEVENSTRATEGY";
	}

	@Override
	public int getBid(GameInfo gi, PlayerInfo pi) {
        int returnBid = 0;
        if (gi.getNumRounds() > 7) {
        	log.debug("using a priori");
        	String[] wordPathsArray = useAPriori( pi.getRack(), gi.getCurrentBidLetter() );
        	//put some stuff to find ideal letters here
        	ArrayList<Word> wordPathsList = convertStringsToWords( wordPathsArray );
        	Word bestFutureWord = getBestWordOfList( wordPathsList );
        	returnBid = bestFutureWord.getScore();
        }
        return returnBid;
	}
}
