package seven.g5;

import seven.g5.gameHolders.*;
import seven.ui.*;

public class Utilities {
	//effective java and headfirst java
	public static float getProbabilityOfLetter(Letter letter1, GameInfo gi)	
	{
		float p = 0;
		System.out.println(gi.getNoOfTurnsRemaining());
		int turns = gi.getNoOfTurnsRemaining();
		for(int i=0;i<=turns;i++) {
			System.out.println((float)gi.getNumberLettersRemaining().get(letter1.getAlphabet()));
			p+= (float)(gi.getNumberLettersRemaining().get(letter1.getAlphabet())+1)/(float)(98-i);
		}
		return p;
	}
}
