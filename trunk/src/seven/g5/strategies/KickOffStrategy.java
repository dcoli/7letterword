package seven.g5.strategies;

import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;

public class KickOffStrategy extends Strategy {

	@Override
	public String getFinalWord() {
		return "balls";
	}

	@Override
	public int getBid(GameInfo gi, PlayerInfo pi) {
		// TODO Auto-generated method stub
		return 0;
	}

}
