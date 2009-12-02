package seven.g5;

import seven.g5.gameHolders.*;
import seven.ui.*;

public class Utilities {
	
	public static float getProbabilityOfLetter(Letter letter1, GameInfo gi)	
	{
		float p = 0;
		int turns = gi.getNoOfTurnsRemaining();
		for(int i=1;i<=turns;i++)
		p+= gi.getNumberLettersRemaining().get(letter1.getAlphabet())/(98-i);
		return p;
	}
}
