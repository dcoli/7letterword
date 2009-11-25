package seven.g3.KnowledgeBase;

import java.io.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import seven.ui.CSVReader;
import seven.g3.KnowledgeBase.*;

public class KnowledgeBase {
	HashSet<Word> wordlist = new HashSet<Word>();
	
    public KnowledgeBase()
    {
        try{
            CSVReader csvreader = new CSVReader(new FileReader("src\\seven\\g3\\KnowledgeBase\\smallwordlist.txt"));
            String[] nextLine;
            //csvreader.readNext(); // Waste the first line
            while((nextLine = csvreader.readNext()) != null)
            {
                String word_str = nextLine[0];
                Word word = new Word(word_str);
                
                if(word.score > 0) {
                	wordlist.add(word);
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("\n Could not load dictionary!");
        }
    }
    
    public PriorityQueue<Word> findMatchingWord(HashMap<Character, Integer> letters, int totalLetters)
    {
    	PriorityQueue<Word> rv = new PriorityQueue<Word>(10, new Word(""));
    	
    	for(Word w : wordlist) {
    		if(w.matchLetters(letters)) {
    			rv.add(w);
    		}
    	}
    	
    	return rv;
    }
}