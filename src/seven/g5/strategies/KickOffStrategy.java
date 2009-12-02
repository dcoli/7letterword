package seven.g5.strategies;

import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;
import seven.g5.Utilities;

public class KickOffStrategy extends Strategy {

	@Override
	public String getFinalWord() {
		return "";
	}

	@Override
	public int getBid(GameInfo gi, PlayerInfo pi) {
		if ( pi.getRack().size() == 0 || pi.getRack().size() == 1 ) {
			if( 	(Utilities.getProbabilityOfLetter(gi.getCurrentBidLetter(),gi) < .20f) &&
					(Utilities.getProbabilityOfLetter(gi.getCurrentBidLetter(),gi) > .05f)) {
				return gi.getCurrentBidLetter().getValue();
			}
		}
		return 0;
	}
}
