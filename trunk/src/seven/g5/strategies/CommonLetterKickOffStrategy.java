package seven.g5.strategies;

import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;
import seven.g5.Utilities;

public class CommonLetterKickOffStrategy extends Strategy {


	public CommonLetterKickOffStrategy() {
		super();
		log.debug("Switching to Common Letter KickOff Strategy! Pow!");
	}

	@Override
	public int getBid(GameInfo gi, PlayerInfo pi) {
		if ( pi.getRack().size() == 0 /*|| pi.getRack().size() == 1*/ ) {
			if (
			gi.getCurrentBidLetter().getAlphabet() == 'E' ||
			gi.getCurrentBidLetter().getAlphabet() == 'A' ||
			gi.getCurrentBidLetter().getAlphabet() == 'I' ||
			gi.getCurrentBidLetter().getAlphabet() == 'O' ||
			gi.getCurrentBidLetter().getAlphabet() == 'N' ||
			gi.getCurrentBidLetter().getAlphabet() == 'R' ||
			gi.getCurrentBidLetter().getAlphabet() == 'T' )
				return (20); //bid for common letters at first
		}
		return 0;
	}
}
