package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

/**
 * An institutional version of the trading agent. 
 * Logic is handled by the parent class and the assigned strategy.
 */
public class TradingAgentInstitutional extends TradingAgent {
    public TradingAgentInstitutional(Trader t, StockExchange e, NewsBoard n) {
        super(t, e, n);
    }
}
