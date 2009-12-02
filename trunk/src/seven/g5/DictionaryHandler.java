package seven.g5;

import java.util.ArrayList;
import java.util.PriorityQueue;

import seven.g5.Logger.LogLevel;
import seven.g5.apriori_ben.DataMine;
import seven.g5.apriori_ben.LetterMine;
import seven.g5.apriori_ben.DataMine.ItemSet;
import seven.g5.apriori_ben.LetterMine.LetterSet;
import seven.g5.data.Word;
import seven.ui.Letter;

public class DictionaryHandler {

	//a priori stuff
	private DataMine mine = null;
	private ItemSet[] answer;
	private Logger log;

	private PriorityQueue<Word> binHeapOfWordsByValue;

	public DictionaryHandler() {
		this.binHeapOfWordsByValue = new PriorityQueue<Word>(1);

		//a priori stuff
		//this.log = new Logger(LogLevel.ERROR, this.getClass());
		this.mine = new LetterMine("src/seven/g5/super-small-wordlist.txt");//src/seven/g5/data/FilteredWords.txt");
		this.mine.buildIndex();
		this.answer = mine.aPriori(0.000001);
	}
	
	public ArrayList<Word> useAPriori(ArrayList<Letter> hand) {
    	ArrayList<Letter> possibleHand = new ArrayList<Letter>(hand);
        LetterSet i = (LetterSet) mine.getCachedItemSet( (String[])(possibleHand.toArray()) );
		String[] words = i.getWords();
        return convertStringsToWords(words);
	}

	public ArrayList<Word> convertStringsToWords( String[] theList ) {
		ArrayList<Word> finalList1 = new ArrayList<Word>();
		for (int i=0; i<theList.length; i++) {
			finalList1.add( new Word( theList[i] ));
		}
		return finalList1;
	}

	public Word getBestWordOfList( ArrayList<Word> listofWords2 ) {
		// TODO Auto-generated method stub
		binHeapOfWordsByValue.clear();
		for( Word w: listofWords2 ) binHeapOfWordsByValue.add(w);
		if( binHeapOfWordsByValue.peek() != null ) return binHeapOfWordsByValue.peek();
		else return null;
	}
	
	// 	EXAMPLE USING A PRIORI - NOTE THAT THE BIDDING STRATEGY IS TERRIBLE THOUGH
	//        int returnBid;
	//        //put some stuff to find ideal letters here
	//        if (totalTurns > 42) {
	//        	String[] wordPathsArray = useAPriori( hand, bidLetter );
	//        	ArrayList<Word> wordPathsList = convertStringsToWords( wordPathsArray );
	//        	bestFutureWord = getBestWordOfList( wordPathsList );
	//        	returnBid = bestFutureWord.getScore();
	//        }
	//        return returnBid;
}
