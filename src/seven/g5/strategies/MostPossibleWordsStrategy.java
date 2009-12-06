/**
 * this class will get the most likely 7 letter word and just bid on those letters. 
 * May be combined with KickOff to throw a rare letter into the mix.
 */

package seven.g5.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import seven.g5.Utilities;
import seven.g5.data.OurLetter;
import seven.g5.data.ScrabbleParameters;
import seven.g5.data.Word;
import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;
import seven.ui.Letter;

public class MostPossibleWordsStrategy extends Strategy {

	public MostPossibleWordsStrategy() {
		super();
		log.debug("Switching to MostPossibleWordsStrategy Mode");
	}

	@Override
	public int getBid(GameInfo gi, PlayerInfo pi) {
		
		ArrayList<Letter> targets = new ArrayList<Letter>();//.getLettersToTarget();
				
		targets = pi.getDictionaryHandler().getLettersWithMostFutureWords( pi, 10 );
		
		Random r = new Random();
		int bid = r.nextInt(3) + pi.getRack().size() + 1;
				
		for( Letter ltr: targets) 
			if( gi.getCurrentBidLetter().getAlphabet().equals( ltr.getAlphabet() ))
				return bid;
		return 0;
	}
}
