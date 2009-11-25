package seven.g2.util;

/**
 * Scrabble Word Class encapsulates word and score
 */
public class ScrabbleWord implements Comparable<ScrabbleWord> {
	String word;
	int score;

	@Override
	public int compareTo(ScrabbleWord o) {
		return score - o.score;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + word + "=" + score + "]";
	}
	
	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word_ the word to set
	 */
	public void setWord(String word_) {
		word = word_;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score_ the score to set
	 */
	public void setScore(int score_) {
		score = score_;
	}
}
