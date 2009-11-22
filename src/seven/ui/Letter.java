/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seven.ui;

/**
 *
 * @author Satyajeet
 */
public class Letter {

	Character alphabet;
    int value;
    
    public Letter(Character c, int s)
    {
        alphabet = c;
        value = s;
    }
    
    public Character getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(Character alphabet) {
		this.alphabet = alphabet;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
    
}
