package ca.yorku.cmg.lob.stockexchange.strategy;

import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.tradingagent.TradingAgent;

public class AggressiveStrategy implements ITradingStrategy {
            @Override
            public void determineAction(Event e, TradingAgent agent, int pos, int price) {
            if (e instanceof GoodNews) {
            agent.submitBid(e, price, 100); 
        } else if (e instanceof BadNews && pos > 0) {
            agent.submitAsk(e, price, pos);
        }
    }
}