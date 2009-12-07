package seven.g3.Strategy;

import java.util.ArrayList;
import java.util.HashMap;

import seven.g3.KnowledgeBase.KnowledgeBase;
import seven.ui.Letter;
import seven.ui.PlayerBids;
import seven.ui.SecretState;

public class MetaStrategy extends Strategy {
	protected HashMap<Integer, Strategy> strategies;
	
	protected int strat;
	
	public static final int STRAT_TARGET = 0;
	public static final int STRAT_SEVEN = 1;
	public static final int STRAT_NAIVE = 2;
	public static final int STRAT_OPENING = 3;
	
	public MetaStrategy(KnowledgeBase kb, int totalRounds, ArrayList<String> playerList)
	{
		super(kb, totalRounds, playerList);
		strategies = new HashMap<Integer, Strategy>();
		
		strategies.put(STRAT_SEVEN, new HighFrequencyStrategy(kb, totalRounds, playerList, ""));
		strategies.put(STRAT_NAIVE, new NaiveStrategy(kb, totalRounds, playerList));
		strategies.put(STRAT_TARGET, new TargetStrategy(kb, totalRounds, playerList, TargetStrategy.bestWords()));
		strategies.put(STRAT_OPENING, new OpeningStrategy(kb, totalRounds, playerList));
	
		strat = STRAT_OPENING;
	}

	@Override
	public int calculateBidAmount(Letter bidLetter,
			HashMap<Character, Integer> letters) {
		Strategy currentStrategy = getCurrentStrategy();
		
		int bid = currentStrategy.calculateBidAmount(bidLetter, letters);
		
		if(bid == -1 && strat == STRAT_OPENING)
		{
			strat = STRAT_SEVEN;
			currentStrategy = getCurrentStrategy();
		}
		else if(bid == -1)
		{
			strat = STRAT_NAIVE;
			currentStrategy = getCurrentStrategy();
		}
		
		return currentStrategy.calculateBidAmount(bidLetter, letters);
	}

	@Override
	public String returnWord(HashMap<Character, Integer> myLetters) {
		
		return getCurrentStrategy().returnWord(myLetters);
	}

	@Override
	public void update(Letter bidLetter, ArrayList<PlayerBids> PlayerBidList,
			SecretState secretstate, int numLetters,
			HashMap<Character, Integer> letters) 
	{
		//Update all strategies?
		for(Strategy s : strategies.values()) {
			s.update(bidLetter, PlayerBidList, secretstate, numLetters, letters);
		}
		
		/* Switch to new strategy, if appropriate */
	}
	
	public Strategy getCurrentStrategy()
	{	
		if(strategies.containsKey(strat)) {
			return strategies.get(strat);
		}
		else {
			strat = STRAT_NAIVE;  // defaults to NAIVE; we can default to something else, too
			if(!strategies.containsKey(strat)) {
				strategies.put(strat, new NaiveStrategy(kb, totalRounds, playerList));
			}

			return strategies.get(strat);
		}
	}

	public String getCurrentStrategyName()
	{
		switch(strat)
		{
		case STRAT_NAIVE:
			return "Naive Strategy";
		case STRAT_TARGET:
			return "Targetted Strategy";
		case STRAT_SEVEN:
			return "Seven Letter Strategy";
		
		default:
			return "Invalid";		
		}
	}
}
