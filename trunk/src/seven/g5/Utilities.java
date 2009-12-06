package seven.g5;

import java.util.ArrayList;
import java.util.List;

import seven.g5.data.Word;
import seven.g5.gameHolders.*;
import seven.ui.*;

public class Utilities {
	//effective java and headfirst java
	public static double getProbabilityOfLetter(Letter letter1, GameInfo gi)	
	{
		double p = 0;
		////System.out.println(gi.getNoOfTurnsRemaining());
		int turns = gi.getNoOfTurnsRemaining();
		for(int i=0;i<=turns;i++) {
			////System.out.println((double)gi.getNumberLettersRemaining().get(letter1.getAlphabet()));
			p+= (double)(gi.getNumberLettersRemaining().get(letter1.getAlphabet()))/(double)(98-i);
		}
		return p;
	}
	
	public static void printLetters(List<Letter> hand) {
		for(Letter l : hand) {
			System.out.print(l.getAlphabet() + ", ");
		}
		//System.out.println();
	}
	
	public static ArrayList<Word> calculateProbabilitiesOfWord(ArrayList<Word> allFutureWords,
			GameInfo gi) {
		double prob;
		for( Word w: allFutureWords ) {
			prob = 1;
			String s = w.toString();
			//chance of each of the letters coming up in one turn
			for ( int c = 0; c < s.length(); c++ ) {
				prob *= ((double)gi.getNumberLettersRemaining().get(s.charAt(c))) / (double)(gi.getTotalLettersRemaining());
			}
			//chance of all letters coming up by end of game
			prob *= gi.getNoOfTurnsRemaining();
			w.setProbability(prob);
		}
		return allFutureWords;
	}

	public static ArrayList<Word> collectOnlySevenLetters(ArrayList<Word> allFutureWords) {
		for( int i=0; i<allFutureWords.size(); i++ ) {
			if( allFutureWords.size() < 7 ) allFutureWords.remove(i);
		}
		return allFutureWords;
	}
}
