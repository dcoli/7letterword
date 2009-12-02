package seven.g5.strategies;

import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;

public class ScaledStrategy extends Strategy {

	public ScaledStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getBid(GameInfo gi, PlayerInfo pi) {
		int numLettersToLookAhead = gi.getPlayerList().size();
		if( numLettersToLookAhead > 7 ) numLettersToLookAhead = 7;
		return 0;
	}

	@Override
	public String getFinalWord() {
		// TODO Auto-generated method stub
		return null;
	}

}
