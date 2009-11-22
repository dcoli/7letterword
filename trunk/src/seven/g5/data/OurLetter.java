package seven.g5.data;

public class OurLetter extends seven.ui.Letter {

    //from parent class
	private Character alphabet;
    private int value;
	//added
    private int numberInGame;
	
	public OurLetter(Character c, int s) {
		super(c,s);
		numberInGame = ScrabbleParameters.getCount( c );
	}
	
	/**
	 * @param numberOfTurnsLeft the number of characters that are still to be drawn, a function of the number of players * 7 - number of characters dispensed so far
	 * @return the value of the letter times the chance of getting it.
	 */
	public float getAdjustedValue( int numberOfTurnsLeft ) {
		float probability = (float)(numberInGame-numberSeen)/(float)numberOfTurnsLeft;
		return (float)value * probability;
	}
}
