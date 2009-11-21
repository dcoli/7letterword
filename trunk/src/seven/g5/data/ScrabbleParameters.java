package seven.g5.data;

public final class ScrabbleParameters {

	//not currently used
	public enum Characters {
		A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
	}
	
	//satya's method in Scrabble doesn't return values we can use
	public static int getCount( Character whichLetter ) {
		if (whichLetter == 'A') return 9;
		if (whichLetter == 'B') return 2;
		if (whichLetter == 'C') return 2;
		if (whichLetter == 'D') return 4;
		if (whichLetter == 'E') return 12;
		if (whichLetter == 'F') return 2;
		if (whichLetter == 'G') return 3;
		if (whichLetter == 'H') return 2;
		if (whichLetter == 'I') return 9;
		if (whichLetter == 'J') return 1;
		if (whichLetter == 'K') return 1;
		if (whichLetter == 'L') return 4;
		if (whichLetter == 'M') return 2;
		if (whichLetter == 'N') return 6;
		if (whichLetter == 'O') return 8;
		if (whichLetter == 'P') return 2;
		if (whichLetter == 'Q') return 1;
		if (whichLetter == 'R') return 6;
		if (whichLetter == 'S') return 4;
		if (whichLetter == 'T') return 6;
		if (whichLetter == 'U') return 4;
		if (whichLetter == 'V') return 2;
		if (whichLetter == 'W') return 2;
		if (whichLetter == 'X') return 1;
		if (whichLetter == 'Y') return 2;
		if (whichLetter == 'Z') return 1;
		return 0;
	}
	
//damnit, this already existed in satya's scrabble class
/*	public static int getScore( String character ) {
		if (character == Characters.A) return 1;
		if (character == Characters.B) return 3;
		if (character == Characters.C) return 3;
		if (character == Characters.D) return 2;
		if (character == Characters.E) return 1;
		if (character == Characters.F) return 4;
		if (character == Characters.G) return 2;
		if (character == Characters.H) return 4;
		if (character == Characters.I) return 1;
		if (character == Characters.J) return 8;
		if (character == Characters.K) return 5;
		if (character == Characters.L) return 1;
		if (character == Characters.M) return 3;
		if (character == Characters.N) return 1;
		if (character == Characters.O) return 1;
		if (character == Characters.P) return 3;
		if (character == Characters.Q) return 10;
		if (character == Characters.R) return 1;
		if (character == Characters.S) return 1;
		if (character == Characters.T) return 1;
		if (character == Characters.U) return 1;
		if (character == Characters.V) return 4;
		if (character == Characters.W) return 4;
		if (character == Characters.X) return 8;
		if (character == Characters.Y) return 4;
		if (character == Characters.Z) return 10;
		return 0;
	}
*/	
}
