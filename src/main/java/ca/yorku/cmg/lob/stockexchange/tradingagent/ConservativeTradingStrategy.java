package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.tradestandards.IOrder;

/**
 * Conservative implementation of {@link ITradingStrategy}.
 */
public class ConservativeTradingStrategy implements ITradingStrategy {

	private final Trader trader;
	private final StockExchange exchange;

	public ConservativeTradingStrategy(Trader trader, StockExchange exchange) {
		this.trader = trader;
		this.exchange = exchange;
	}

	@Override
	public void actOnEvent(Event e, int pos, int price) {
		IOrder newOrder = null;

		if (e instanceof GoodNews) {
			newOrder = new Bid(trader, e.getSecrity(), (int) Math.round(price * 1.05),
					(int) Math.round(pos * 0.2), e.getTime());
		} else if (e instanceof BadNews) {
			newOrder = new Ask(trader, e.getSecrity(), (int) Math.round(price * 0.95),
					(int) Math.round(pos * 0.2), e.getTime());
		} else {
			System.out.println("Unknown event type");
		}

		if (newOrder != null) {
			exchange.submitOrder(newOrder, e.getTime());
		}
	}
}

