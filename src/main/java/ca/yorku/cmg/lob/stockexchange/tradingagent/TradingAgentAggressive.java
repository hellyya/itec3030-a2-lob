package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

/**
 * Legacy aggressive agent preserved for compatibility. Internally delegates to an {@link AggressiveTradingStrategy}.
 */
public class TradingAgentAggressive extends TradingAgent {

	public TradingAgentAggressive(Trader t, StockExchange e, NewsBoard n) {
		super(t, e, n, new AggressiveTradingStrategy(t, e));
	}

	@Override
	protected void actOnEvent(ca.yorku.cmg.lob.stockexchange.events.Event e, int pos, int price) {
		if (strategy != null) {
			strategy.actOnEvent(e, pos, price);
		}
	}
}
