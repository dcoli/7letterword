package seven.g5.strategies;

import java.util.ArrayList;

import seven.g5.data.Word;
import seven.g5.gameHolders.GameInfo;
import seven.g5.gameHolders.PlayerInfo;
import seven.ui.Letter;

public class EmpiricalFrameworkStrategy extends Strategy {

	
	public EmpiricalFrameworkStrategy() {
		super();
		System.out.println("Switching to Empirical Mode! Zoooom!");
	}

	@Override
	public int getBid(GameInfo gi, PlayerInfo pi) {
		//make new fake hand with new letter
		ArrayList<Letter> tempHand = new ArrayList<Letter>(pi.getRack());
		tempHand.add(gi.getCurrentBidLetter());
		
		System.out.println("Hand! " + tempHand.toString());
		
		ArrayList<Word> allFutureWords = pi.getDictionaryHandler().futureAnagram(tempHand);
		
		for(Word w : allFutureWords) {
			if(w.toString().length() == 7) {
				return gi.getCurrentBidLetter().getValue() + 1;
			}
		}
		return 0;
	}
}
