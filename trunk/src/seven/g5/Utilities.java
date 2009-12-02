package seven.g5;

import seven.g5.gameHolders.*;
import seven.ui.*;

public class Utilities {
	
	public static float getProbabilityOfLetter(Letter letter1, GameInfo gi)	
	{
		return gi.getNumberLettersRemaining().get(letter1.getAlphabet())/gi.getNoOfTurnsRemaining();		
	}
}
