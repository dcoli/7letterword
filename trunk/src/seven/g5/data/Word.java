package seven.g5.data;

public class Word {

	private String string;
	/**
	 * the sum of integer values of all letters
	 */
	private int score;
	
	/**
	 * the 
	 */
	private float weightedScore;

	public Word( String stringRepresentation ) {
		this.setString(stringRepresentation);
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(float weightedScore) {
		this.weightedScore = weightedScore;
	}

	/**
	 * @return the score
	 */
	public float getWeightedScore() {
		return weightedScore;
	}

	/**
	 * @param value the value to set
	 */
	public void setScore(int value) {
		this.score = value;
	}

	/**
	 * @return the value
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param string the string to set
	 */
	public void setString(String string) {
		this.string = string;
	}

	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}
}
