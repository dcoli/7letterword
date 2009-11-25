package seven.g1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;

import seven.ui.CSVReader;
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
public class G1Player implements Player{

	ArrayList<Word> wordlist=new ArrayList<Word>();
	ArrayList<Letter> openletters= new ArrayList<Letter>();
	Boolean first =true;
	ArrayList<String> possiblities= new ArrayList<String>();
	ArrayList<PlayerBids> RefList= new ArrayList<PlayerBids>();
    public void Register() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public int Bid(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList, int total_rounds,ArrayList<String> PlayerList, SecretState secretstate, int PlayerID) {
    	RefList=PlayerBidList;
    	if(first){
    		openletters=secretstate.getSecretLetters();
    	}
    	/*if(!first){
//    		PlayerBids LastBid= PlayerBidList.get(0);
    	PlayerBids LastBid= PlayerBidList.get(PlayerBidList.size()-1);
 //  	System.out.println(PlayerBidList.size());
    	System.out.println(LastBid.getTargetLetter().getAlphabet());
    	//if(LastBid.getWonBy().equals("seven.G1Player")){
    		openletters.add(LastBid.getTargetLetter());
    	//}
    	}*/
    	first =false;
        return 0;
    }

    public String returnWord() {
    	
    	for(int i=0; i<RefList.size();i++){
    		openletters.add(RefList.get(i).getTargetLetter());
    	}
    	String s= new String();
    	char[] c= new char[openletters.size()];
    	for(int i=0; i<openletters.size();i++){
    		 c[i]= openletters.get(i).getAlphabet();
    	}
    	s= String.valueOf(c);
    	Word open= new Word(s);
    	System.out.println("Open Letters are:" + s);
    	initDict();
    	
    	for (int i=0;i<wordlist.size();i++){
    		if(open.issubsetof(wordlist.get(i))){
    			possiblities.add(wordlist.get(i).getWord());
    		}
    	}
    	int max=0;
    	int maxindex=0;
    	for(int i=0;i<possiblities.size();i++){
    		if(max<possiblities.get(i).length()){
    			max=possiblities.get(i).length();
    			maxindex=i;
    		}
    	}
    	
        //throw new UnsupportedOperationException("Not supported yet.");
    	if(possiblities.size()>0){
    		System.out.println(possiblities.get(maxindex));
    		return possiblities.get(maxindex).trim();
    	}else
    		return " ";
    }

    public  void initDict()
    {
        try{
            CSVReader csvreader = new CSVReader(new FileReader("FilteredWords.txt"));
            System.out.println("reached 1");
            String[] nextLine;
            csvreader.readNext(); // Waste the first line
            while((nextLine = csvreader.readNext()) != null)
            {
            	
                String word = nextLine[2];
                Word tempword= new Word(word);
               // System.out.println("reached 2");
                wordlist.add(tempword);
               // System.out.println("addedword "+ word);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("\n Could not load dictionary!");
        }

    }
   

}
