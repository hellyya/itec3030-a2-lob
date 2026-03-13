package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

/**
 * Trading agent representing an institutional trader.
 */
public class TradingAgentInstitutional extends TradingAgent {

	public TradingAgentInstitutional(Trader t, StockExchange e, NewsBoard n, ITradingStrategy strategy) {
		super(t, e, n, strategy);
	}

	@Override
	protected void actOnEvent(ca.yorku.cmg.lob.stockexchange.events.Event e, int pos, int price) {
		if (strategy != null) {
			strategy.actOnEvent(e, pos, price);
		}
	}
}

