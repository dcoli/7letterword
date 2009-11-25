package seven.g5.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.io.FileReader;
import java.util.*;

import seven.ui.CSVReader;

import seven.g5.Logger;
import seven.g5.Logger.LogLevel;
import seven.g5.data.OurLetter;
import seven.g5.data.ScrabbleParameters;
import seven.g5.data.Word;
import seven.ui.Letter;
import seven.ui.PlayerBids;
import seven.ui.SecretState;

public class SimpleStrategy extends Strategy {

        //monal
        private PlayerBids cpbid = null ;
        private Letter letter ;
        int size = 0;
        int futureSize = 0;
        ArrayList<Letter> currentLettersToBidFor = new ArrayList<Letter>();
        ArrayList<String> ListofWords = new ArrayList<String>();
        char[] letters = new char[10] ;
        char[] futureWords = new char[10] ;
        Dictionary sowpods = new Dictionary();
        int currentTurn = 0;
     
        //colin
        //private OurLetter currentLetter;
        //private ArrayList<Letter> hand = new ArrayList<Letter>();
        //initialized in the Strategy constructor
        //protected HashMap<Character, Integer> numberLettersRemaining = new HashMap<Character, Integer>();
       
        PriorityQueue<Word> binHeapOfCurrentWords = new PriorityQueue<Word>(1,
                        new Comparator<Word>() {
                                 public int compare(Word a, Word b)
                                 {
                                   float scoreA = a.getScore();
                                   float scoreB = b.getScore();
                                   if (scoreB>scoreA)
                                        return 1;
                                   else if (scoreB<scoreA)
                                        return -1;
                                   else
                                        return 0;
                                 }
                        }
        );
       
        public SimpleStrategy () {
                super();
                loadDictionary();
        }
       
    private void loadDictionary() {
        try{
            CSVReader csvreader = new CSVReader(new FileReader("src/seven/g5/data/FilteredWords.txt"));
		    String[] nextLine;
		    //csvreader.readNext(); // Waste the first line
		    while((nextLine = csvreader.readNext()) != null)
		    {
		        String word = nextLine[0];
		        sowpods.wordlist.put(word, Boolean.TRUE);
		    }

        } catch(Exception e)
	    {
	        e.printStackTrace();
	        System.out.println("\n Could not load dictionary!");
	    }
	}

	//protected OurLetter currentLetter;
    protected ArrayList<Letter> hand = new ArrayList<Letter>();
  
    /**
     *
     * @return the value of the bid to place
     */

    public int getBid(Letter bidLetter, ArrayList<PlayerBids> playerBidList,
            int totalRounds, ArrayList<String> playerList, SecretState secretstate, int playerID) {

        //get the letters we start with
        if (currentTurn++ == 0) {
            this.hand = secretstate.getSecretLetters();
            int letterPosition=0;
            for(Letter l: this.hand){
            	letters[letterPosition++] = l.getAlphabet();
            	size++;
            }
            for (int i = 0; i<this.hand.size(); i++) {
                decrementLettersRemaining(hand.get(i));
            }
        }

        this.letter = bidLetter;
       
        //track how many of each letter remains (as far as we can tell)
        if (this.letter != null) decrementLettersRemaining( this.letter );

        this.playerList = playerList;
        if( playerBidList != null && playerBidList.size() > 0 ) {
            PlayerBids currentPlayerBids = (PlayerBids)(playerBidList.get(playerBidList.size()-1));
            //System.out.println(currentPlayerBids.getWonBy());
            //System.out.println(playerBidList.get(playerBidList.size()-1).getWonBy());
            if( (currentPlayerBids.getWonBy().equals("seven.g5.G5_Scrabblista"))){
                //System.out.println("We won !!!");
                this.bidpoints -= currentPlayerBids.getWinAmmount();
                getLetterList(currentPlayerBids.getTargetLetter());
            }       
        }
        
        //put some stuff to find ideal letters here
        currentLettersToBidFor = getBidworthyLetters();
        for(Letter l: currentLettersToBidFor) {
        	if(l.getAlphabet() == bidLetter.getAlphabet()) {
        		return l.getValue();
        	}
        }
        return 0;
//       Random rand = new Random();
//       int val = 1+rand.nextInt(4);
//       return val;
    }
   
    protected void decrementLettersRemaining(Letter letter2) {
        int oldAmount = numberLettersRemaining.get(letter2.getAlphabet());
        numberLettersRemaining.put(letter2.getAlphabet(), --oldAmount );
    }

//    /**
//     * @param hand the arraylist of characters currently in our hand
//     * @return optimal word
//     */
//    protected String getOptimalWordFromHand( ArrayList<Letter> hand1 ) {
//        return getBestWordOfList(getListOfFoundWords( hand1 ));
//    }
//   
//    protected String getOptimalWordFromFuture( ArrayList<Letter> hand1 ) {
//        //here add every possible permutation of characters to fill out the rest of the hand
//        //then we'll see what words we can create with those
//        //then calculate the percentage chance of getting those letters (each Word's weightedScore)
//        return null;
//    }
   
    protected String getBestWordOfList( ArrayList<Word> listOfFoundWords ) {
        // TODO Auto-generated method stub
        binHeapOfCurrentWords.clear();
        for( Word w: listOfFoundWords ) binHeapOfCurrentWords.add(w);
        if( binHeapOfCurrentWords.peek() != null ) return binHeapOfCurrentWords.peek().toString();
        else return "";
    }


//    protected ArrayList<Word> getListOfFoundWords( ArrayList<Letter> hand2 ) {
//        // TODO Auto-generated method stub
//        //this is what will call the anagram solver
//        return null;
//    }

    public String getFinalWord() {
        getListofPossibleWords();
        ArrayList<Word> finalList = convertStringsToWords(ListofWords);
        String bestWord = getBestWordOfList( finalList );
        unloadDictionary();
        if ( bestWord != null ) return bestWord;
        else return "";
    }
   
 
        private void unloadDictionary() {
        	sowpods.wordlist.clear();
        }

		private ArrayList<Word> convertStringsToWords( ArrayList<String> theList ) {
                ArrayList<Word> finalList1 = new ArrayList<Word>();
                for (int i=0; i<theList.size(); i++) {
                        finalList1.add( new Word( theList.get(i)));
                }
                return finalList1;
        }
       
        public void getListofPossibleWords(){
                //getWordlist();
                        solve() ;
                        //solve(0);
                //return ListofWords;
        }
        void solve()
        {
            int[] freq = new int[26] ;
            int[] tp = new int[26] ;
            for(int i=0;i<26;++i) freq[i] = 0 ;
            for(int i=0;i<size;++i) ++freq[letters[i]-'A'] ;
            for(int i=0;i<size;++i) System.out.print(letters[i] + " ") ;
            //System.out.println("") ;
            for(String str : sowpods.wordlist.keySet()) if(sowpods.wordlist.get(str)){
               
                for(int i=0;i<26;++i) tp[i] = 0 ;
                int i = 0 ;
                for(;i<str.length();++i)
                {
                    char ch = str.charAt(i) ;
                    ++tp[ch-'A'] ;
                    if(tp[ch-'A'] > freq[ch-'A']) break ;
                }
                if(i < str.length()) continue ;
                //int k = 0 ;
                //for(;k<26;++k) if(tp[k] > freq[k]) continue ;
                ListofWords.add(str) ;
            }
        }

        
        /**
	     *
	     * @return final word to return at the end of the round
	     */
	    public ArrayList<Letter> getBidworthyLetters() {
	    	ArrayList<Letter> goodLetters = new ArrayList<Letter>();
	    	HashMap<Letter,ArrayList<String>> possibleWords = new HashMap<Letter,ArrayList<String>>();
	    	possibleWords = getListofPossibleWords(possibleWords);
	    	for(Letter ltr: possibleWords.keySet()) {
	    		if( possibleWords.get(ltr).size() > 0 ) {
	    			goodLetters.add(ltr);
	    		}
	    	}
	    	return goodLetters;
	    }
           
        public HashMap<Letter,ArrayList<String>> getListofPossibleWords( HashMap<Letter,ArrayList<String>> possibleWordsByLetter ){
                //getWordlist();
//                try{
//                        CSVReader csvreader = new CSVReader(new FileReader("src/seven/g5/data/FilteredWords.txt"));
//                String[] nextLine;
//                //csvreader.readNext(); // Waste the first line
//                while((nextLine = csvreader.readNext()) != null)
//                {
//                    String word = nextLine[0];
//                    sowpods.wordlist.put(word, Boolean.TRUE);
//                }
//
//                        } catch(Exception e)
//                {
//                    e.printStackTrace();
//                    System.out.println("\n Could not load dictionary!");
//                }
                ArrayList<Letter> possibleHand = hand;
                for (int i=0; i<26; i++) {
                	char c = (char)(i+'A');
                	possibleHand.add(new Letter(c,ScrabbleParameters.getScore(c)));
                	solveFuture(possibleHand,possibleWordsByLetter) ;
                	possibleHand.remove(possibleHand.size()-1);
                }
                return possibleWordsByLetter;
        }
        void solveFuture(ArrayList<Letter> hand1, HashMap<Letter, ArrayList<String>> possibleWordsByLetter)
        {
        	ArrayList<String> wordsForLetter = new ArrayList<String>();
            int[] freq = new int[26] ;
            int[] tp = new int[26] ;
            for(int i=0;i<26;++i) freq[i] = 0 ;
            for(int i=0;i<hand1.size();++i) ++freq[hand1.get(i).getAlphabet()-'A'];
            //for(int i=0;i<hand1.size();++i) System.out.print(hand1.get(i).getAlphabet()) ;
            //System.out.println("") ;
            for(String str : sowpods.wordlist.keySet()) if(sowpods.wordlist.get(str)){
                for(int i=0;i<26;++i) tp[i] = 0 ;
                int i = 0 ;
                for(;i<str.length();++i)
                {
                    char ch = str.charAt(i) ;
                    ++tp[ch-'A'] ;
                    if(tp[ch-'A'] > freq[ch-'A']) break ;
                }
                if(i < str.length()) continue ;
                //int k = 0 ;
                //for(;k<26;++k) if(tp[k] > freq[k]) continue ;
                wordsForLetter.add(str);
                //ListofWords.add(str) ;
            }
            possibleWordsByLetter.put(hand1.get(hand1.size()-1),wordsForLetter);
        }
       
        public void getLetterList(Letter x){
                char c = x.getAlphabet();
                ++size;
                //there's a bug here
                letters[size-1] = c;            
                //System.out.println("Word list: "+words);
        }

//        public void getFutureWordlist(Letter x){
//            char c = x.getAlphabet();
//            ++futureSize;
//            //there's a bug here
//            futureWords[futureSize-1] = c;
//            //System.out.println("Word list: "+words);
//        }

        
//        public static void main( String[] args ) {
//                SimpleStrategy strat = new SimpleStrategy();
//                ArrayList<Letter> myHand = new ArrayList<Letter>();
//               
//                //ArrayList<String> myWordList = new ArrayList<String>();              
//                strat.binHeapOfCurrentWords.add(new Word("CAT"));
//                strat.binHeapOfCurrentWords.add(new Word("RAT"));
//                strat.binHeapOfCurrentWords.add(new Word("HAT"));
//               
//                while( strat.binHeapOfCurrentWords.size() > 0 ) {
//                       // System.out.println("word "+strat.binHeapOfCurrentWords.peek()+" is "+((Word)strat.binHeapOfCurrentWords.poll()).getScore() );
//                }
//        }
   


        public void getWordlist(){
                char c = letter.getAlphabet();
                ++size;
                letters[size-1] = c;
               
        }


   

class Dictionary
{
    Hashtable<String,Boolean> wordlist;

    public Dictionary()
    {
        wordlist = new Hashtable<String, Boolean>();
    }
}
       
        /**
         * @param numberOfTurnsLeft the number of characters that are still to be drawn, a function of the number of players * 7 - number of characters dispensed so far
         * @return the value of the letter times the chance of getting it.
         */
        public float getAdjustedValueOfWord( int numberOfTurnsLeft, String targetWord ) {
//              TODO
//              float probability = (float)(numberInGame-numberSeen)/(float)numberOfTurnsLeft;
//              return (float)value * probability;
                return 0;
        }

}