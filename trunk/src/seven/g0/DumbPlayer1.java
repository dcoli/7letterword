package seven.g0;

import java.util.ArrayList;
import java.util.Random;
import seven.ui.Letter;
import seven.ui.Player;
import seven.ui.PlayerBids;
import seven.ui.SecretState;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Satyajeet
 */
public class DumbPlayer1 implements Player{

    public void Register() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public int Bid(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList, int total_rounds,ArrayList<String> PlayerList, SecretState secretstate, int PlayerID) {
        //throw new UnsupportedOperationException("Not supported yet.");
       Random rand = new Random();
        int val = 3+rand.nextInt(4);
        return val;
        //return 0;
    }

    public String returnWord() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return "AT";
    }

   

}
