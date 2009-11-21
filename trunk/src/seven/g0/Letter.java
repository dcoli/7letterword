package seven.g0;

public class Letter {

	private int score;
	private int numberInGame;
	private int numberSeen;
	
	/**
	 * @param numberOfTurnsLeft the number of characters that are still to be drawn, a function of the number of players * 7 - number of characters dispensed so far
	 * @return the score of the letter times the chance of getting it.
	 */
	public float getAdjustedScore( int numberOfTurnsLeft ) {
		float probability = (float)(numberInGame-numberSeen)/(float)numberOfTurnsLeft;
		return (float)score * probability;
	}
	
	
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param numberInGame the numberInGame to set
	 */
	public void setNumberInGame(int numberInGame) {
		this.numberInGame = numberInGame;
	}
	/**
	 * @return the numberInGame
	 */
	public int getNumberInGame() {
		return numberInGame;
	}
	/**
	 * @param numberSeen the numberSeen to set
	 */
	public void setNumberSeen(int numberSeen) {
		this.numberSeen = numberSeen;
	}
	/**
	 * @return the numberSeen
	 */
	public int getNumberSeen() {
		return numberSeen;
	}
	
}
