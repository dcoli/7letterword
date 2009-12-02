package seven.g5;

import seven.g5.gameHolders.*;
import seven.ui.*;
import java.util.*;

public class Utilities {

	public HashMap<Character, Integer> numberLettersRemaining;
	int sumofLettersRemaining;
	
	public float getProbabilityOfLetter(Letter letter1, GameInfo gi)	
	{
		numberLettersRemaining = gi.getNumberLettersRemaining();
		return numberLettersRemaining.get(letter1.getAlphabet())/gi.getNoOfTurnsRemaining();		
	}
}
