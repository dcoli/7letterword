package seven.g5;

import seven.g5.gameHolders.*;
import seven.ui.*;

public class Utilities {
	
	public static float getProbabilityOfLetter(Letter letter1, GameInfo gi)	
	{
		float p = 0;
		int turns = gi.getNoOfTurnsRemaining();
		for(int i=0;i<=turns;i++)
		p+= (float)gi.getNumberLettersRemaining().get(letter1.getAlphabet())/(float)(98-i);
		return p;
	}
}
