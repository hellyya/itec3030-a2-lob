package ca.yorku.cmg.lob.stockexchange.strategy;

import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.tradingagent.TradingAgent;

public class ConservativeStrategy implements ITradingStrategy {
    @Override
    public void determineAction(Event e, TradingAgent agent, int pos, int price) {
        if (e instanceof GoodNews) {
            // Conservative: Take profit! Sell a small portion (e.g., 10 shares) if we have them
            if (pos >= 10) {
                agent.submitAsk(e, price, 10);
            }
        } 
        else if (e instanceof BadNews) {
            // Conservative: Buy the dip! Buy a small amount (e.g., 10 shares) when news is bad
            agent.submitBid(e, price, 10);
        }
    }
}