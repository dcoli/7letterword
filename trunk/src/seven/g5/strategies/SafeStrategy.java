package seven.g5.strategies;

import java.util.ArrayList;
import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;
import seven.g5.data.Word;
import seven.ui.Letter;

public class SafeStrategy extends Strategy {

	private ArrayList<Letter> rack;
	private Word bestWord = null;
	
	@Override
	public int getBid(GameInfo gi, PlayerInfo pi) {
		this.rack = pi.getRack();
		return 0;
	}

	@Override
	public String getFinalWord() {
		// TODO Auto-generated method stub
		return this.bestWord.toString();
	}

}
