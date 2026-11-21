package ca.yorku.cmg.lob.stockexchange.strategy;

import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.tradingagent.TradingAgent;

public interface ITradingStrategy {
    // The strategy needs the Event and the Agent to execute the trade
    void determineAction(Event e, TradingAgent agent, int pos, int price);
}