package seven.g5.strategies;

import java.util.ArrayList;

import seven.g5.data.Word;
import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;

public class LessThanSevenLetterStrategy extends Strategy {

	@Override
	public int[] getBid(GameInfo gi, PlayerInfo pi) {
		
		for( int c=0; c<26; c++ ) {
			ArrayList<Word> endWordList = pi.getDictionaryHandler().pastAnagram(pi.getRack());
		}
		
		return null;
	}

}
