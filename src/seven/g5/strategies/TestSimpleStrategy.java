package seven.g5.strategies;

import java.util.ArrayList;

import seven.g5.data.OurLetter;
import seven.g5.data.Word;

public class TestSimpleStrategy extends SimpleStrategy {

	public TestSimpleStrategy() {
		// TODO Auto-generated constructor stub
	}

	public static void main( String[] args ) {
		SimpleStrategy strat = new SimpleStrategy();
		ArrayList<OurLetter> myHand = new ArrayList<OurLetter>();

		//ArrayList<String> myWordList = new ArrayList<String>();		
		strat.binHeapOfWordsByValue.add(new Word("CAT"));
		strat.binHeapOfWordsByValue.add(new Word("RAT"));
		strat.binHeapOfWordsByValue.add(new Word("HAT"));
				
		while( strat.binHeapOfWordsByValue.size() > 0 ) {
			System.out.println("word "+strat.binHeapOfWordsByValue.peek()+" is "+((Word)strat.binHeapOfWordsByValue.poll()).getScore() );
		}
	}
	
}
