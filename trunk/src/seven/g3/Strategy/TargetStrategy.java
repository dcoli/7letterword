package seven.g3.Strategy;

import java.util.ArrayList;
import java.util.HashMap;

import seven.g3.KnowledgeBase.KnowledgeBase;
import seven.ui.Letter;
import seven.ui.PlayerBids;
import seven.ui.Scrabble;
import seven.ui.SecretState;

public class TargetStrategy extends Strategy {
	
	ArrayList<String> targets;
	HashMap<String, ArrayList<Character>> remainingLetters;
	int totalLetters;
	int totalLengths;
	char previousChar;
	
	public static final double FREQUENCY_MODIFIER = 0.75;
	public static final int MAX_LETTERS_BEFORE_CHANGE = 5;
	
	public TargetStrategy(KnowledgeBase kb, int totalRounds, ArrayList<String> playerList, ArrayList<String> targets)
	{
		super(kb, totalRounds, playerList);
		this.targets = targets;
		
		remainingLetters = new HashMap<String, ArrayList<Character>>();
		
		totalLengths = 0;
		
		//Maps each target word to an array of what letters we have left to attain
		for(String word : targets)
		{
			ArrayList<Character> chars = new ArrayList<Character>();
			for(int i = 0; i < word.length(); i++)
				chars.add(word.charAt(i));
			remainingLetters.put(word, chars);
			
			totalLengths += word.length();
		}
		
		int totalLetters = 0;
	}
	
	public int lettersRemaining(String word)
	{
		if(remainingLetters.containsKey(word))
			return remainingLetters.get(word).size();
		else
			return word.length();
	}

	@Override
	public int calculateBidAmount(Letter bidLetter,
			HashMap<Character, Integer> letters) {
		
		/**
		 *  Return -1 when signal strategy change.
		 */
		if(totalLetters > MAX_LETTERS_BEFORE_CHANGE)
		{
			//Enough letters
			return -1;
		}
		
		if(numPossibleWords() <= 0)
		{
			return -1;
		}
		
		char letter = bidLetter.getAlphabet();
		double freq = 0;
		for(String word : targets)
		{
			ArrayList<Character> remain = remainingLetters.get(word);
			if(remain.contains(letter))
			{
				//Closer to making word, higher value
				freq+= word.length() - remain.size();
			}
		}
		
		int letterScore = Scrabble.letterScore(letter);
		
		//This bidding scheme isn't that good.
		if(freq == 0)
			return 0;
		
		if(freq == 1)
			return letterScore;
		
		//Bid proportional to how many words in the list have that letter
		//Clamped to [letterScore, 3 * letterScore]
		return (int)(letterScore * Math.min(letterScore * 3, Math.max(1, freq * FREQUENCY_MODIFIER)));
	}

	@Override
	public String returnWord(HashMap<Character, Integer> myLetters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList,
			SecretState secretstate, int numLetters,
			HashMap<Character, Integer> letters) {
		// TODO Auto-generated method stub
		
		if(totalLetters == 0)
		{
			//On first run, account for secret letters
			for(char c : letters.keySet())
			{
				updateLetterFrequencies(c);
			}
			totalLetters = numLetters;
			updatePossibleWords();
		}
		else if(numLetters > totalLetters)
		{
			//If win a bid, update the frequencies.
			updateLetterFrequencies(previousChar);
			updatePossibleWords();
		}
		
		previousChar = bidLetter.getAlphabet();

	}
	
	public void updateLetterFrequencies(Character c)
	{
		//Remove character from remaining letters of each word if applicable
		for(String word : targets)
		{
			ArrayList<Character> l = remainingLetters.get(word);
			if(l.contains(c))
				l.remove(c);
		}
	}
	
	public void updatePossibleWords()
	{
		ArrayList<String> wordsToRemove = new ArrayList<String>();
		
		for(String word: targets)
		{
			ArrayList<Character> l = remainingLetters.get(word);
			if(l.size() > 7 - totalLetters)
			{
				wordsToRemove.add(word);
				//targets.remove(word);
				//remainingLetters.remove(word);
			}
		}
		
		for(String word: wordsToRemove)
		{
			targets.remove(word);
			remainingLetters.remove(word);
		}
	}
	
	public int numPossibleWords()
	{
		return targets.size();
	}
	
	public static ArrayList<String> bestWords()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("ANTIAIR");
		list.add("ARENITE");
		list.add("ERINITE");
		list.add("ETAERIO");
		list.add("INERTIA");
		list.add("ORATION");
		list.add("OTARINE");
		list.add("TAENIAE");
		
		return list;
	}

}
