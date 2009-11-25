package seven.g2;

import seven.g2.util.ScrabbleUtility;

/**
 * Class to maintain current round's scrabble bag information. This includes
 * current tile counts for each letter This includes hidden letter counts,seen
 * letter counts and all such information.
 * 
 */

public class ScrabbleBag {

	/**
	 * For each letter maintain count of how many were seen This includes
	 * auctioned letters + personal hidden letters
	 */
	private int[] letterToTileCountSeen = new int[26];
	public int[] lettersLeft = new int[26];
	
	private int totalSeenTiles;

	private int totalUnknownTiles;

	/**
	 * Constructor Initializes counts
	 */
	public ScrabbleBag(int noOfPlayers, int noOfHiddenTiles,
			Character[] hiddenLetters_) {

		/** Init seen tiles counts **/
		for (int i = 0; i < letterToTileCountSeen.length; i++) {
			letterToTileCountSeen[i] = 0;
		}

		totalSeenTiles = 0;

		/** my hidden tiles are not counted **/
		totalUnknownTiles = (noOfPlayers - 1) * noOfHiddenTiles;

		/** Update my hidden tiles as seen tiles **/
		for (Character c : hiddenLetters_) {
			updateSeenTileInformation(c);
		}
		for(int i=0;i<26;i++)
		{
			lettersLeft[i] = ScrabbleUtility.letterToTileCount[i];
		}

	}

	/**
	 * Update seen tile information
	 * 
	 * @param c
	 */
	public void updateSeenTileInformation(Character c) {
		letterToTileCountSeen[c - 'A'] = letterToTileCountSeen[c - 'A'] + 1;
		lettersLeft[c-'A']--;
		totalSeenTiles++;
	}

	/**
	 * Returns the probability of this letter being up for auction in the
	 * remaining auctions.
	 * 
	 * Calculated as 1 - probability of this letter not being picked in n
	 * pickings
	 * 
	 * @param c
	 * @param noOfRemainingAuctions
	 * @return
	 */
	public double getProbabilityOfAuction(Character c, int noOfRemainingAuctions) {

		int totalTilesLeft = ScrabbleUtility.TOTAL_TILE_COUNT - totalSeenTiles
				- totalUnknownTiles;

		double tilesLeftForThisLetter = (ScrabbleUtility
				.getNoOfScrabbleTiles(c) - letterToTileCountSeen[c - 'A'])
				* 1.0
				* totalTilesLeft
				/ (totalTilesLeft + totalUnknownTiles);
		

		double denominator = 1;
		for (int t = totalTilesLeft; t > totalTilesLeft - noOfRemainingAuctions; t--) {
			denominator = denominator * t;
		}

		double numerator = 1;
		for (double t = (totalTilesLeft - tilesLeftForThisLetter); t > totalTilesLeft - tilesLeftForThisLetter - noOfRemainingAuctions; t--) {
			numerator = numerator * t;
		}

		double probOfNotPickingThisLetter = numerator / denominator;

		return 1 - probOfNotPickingThisLetter;
	}

	/**
	 * Returns the probability of these letters being up for auction in the
	 * remaining auctions.
	 * 
	 * @param current
	 * @param required
	 * @param noOfRemainingAuctions
	 * @return
	 */
	public double getProbabilityOfMakingThisWord(Character[] current,
			Character[] required, int noOfRemainingAuctions) {
		return 0;
	}
	
	public static void main(String[] args){
		ScrabbleBag sb = new ScrabbleBag(1,0,new Character[]{});
		sb.updateSeenTileInformation('A');
		System.out.println(sb.getProbabilityOfAuction('A', 2));
	}
}
