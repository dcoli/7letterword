package seven.g2;


import java.util.ArrayList;
import seven.ui.PlayerBids;
import java.util.HashMap;
import seven.g2.util.*;
import seven.g2.util.ScrabbleWord;



class PlayerStatus
{
	String playerName;
	Character[] gotLetters;
	HashMap<Character,ArrayList<Integer>> bidValues = new HashMap<Character,ArrayList<Integer>>();
	ArrayList<ScrabbleWord> possibleWords;
	int noOfLetters;
}

public class PlayersHistory {

	//stores the list of words present with the players
	//HashMap<String,String> playersStatus=new HashMap<String,String>();
	
	ArrayList<PlayerStatus> playersStats =new ArrayList<PlayerStatus>();
	HashMap<String,ArrayList<Integer>> playersBids = new HashMap<String,ArrayList<Integer>>();
	
	//initializes the number of players
	
	PlayersHistory(ArrayList<String> players, int noOfUnknownLetters)
	{
		int size=players.size();
		for(int i=0;i<size;i++)
		{
			playersStats.get(i).playerName=players.get(i);
		}
		/*with the noOfUnknownLetters add tht to Character Set Contained 
		*with the help of Scrabble
		*/
	}
	
	//updates after every bid
	
	public void playerStatusUpdate(Character bidLetter, String wonBy, int bidValue)
	{
		int size=playersStats.size();
		WordList wl=new WordList();
		for(int i =0;i<size;i++)
		{
			if(playersStats.get(i).playerName.equals(wonBy))
			{
				playersStats.get(i).gotLetters[playersStats.get(i).noOfLetters]=bidLetter;
				ArrayList<Integer> bidsForLetter=new ArrayList<Integer>();
				bidsForLetter=playersStats.get(i).bidValues.get(bidLetter);
				bidsForLetter.add(bidValue);
				playersStats.get(i).bidValues.put(bidLetter,bidsForLetter);
				playersStats.get(i).noOfLetters++;
				
				/*try updating the possible words that could be formed with the letters the players possess
				*also update the possible bid values for each alphabet for the players
				*/
				String letters=new String();
				letters="";
				for(int j=0;j<playersStats.get(i).gotLetters.length;j++)
				{
					letters+=playersStats.get(i).gotLetters[j].charValue();
				}
				playersStats.get(i).possibleWords=wl.getValidWords(letters);
				break;
			}
		}
		
	}
	
	public ArrayList<PlayerStatus> getPlayersStatus()
	{
		
		return playersStats;
	}
	
	
	public HashMap<String,ArrayList<Integer>> possibleBids(Character bidLetter)
	{
		/*compute what a player might bid for that Letter using previous bid values for that letter
		* and possible words a player might form with those letters
		*/
		return playersBids;		
	}
	
	//public storeBidResults()
	 
}
