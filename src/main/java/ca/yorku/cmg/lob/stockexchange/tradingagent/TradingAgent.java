package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.stockexchange.strategy.ITradingStrategy; 
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.tradestandards.IOrder;

/**
 * An trading agent that receives news and reacts by submitting ask or bid orders.
 */
public abstract class TradingAgent implements INewsObserver {
    protected Trader t;
    protected StockExchange exc;
    protected NewsBoard news;
    protected ITradingStrategy strategy;
    
    /**
     * Constructor
     */
    public TradingAgent(Trader t, StockExchange e, NewsBoard n) {
        this.t = t;
        this.exc = e;
        this.news = n;
        // Register this agent to the NewsBoard so it can receive "Pushes"
        n.registerObserver(this);
    }

    /**
     * This is the Setter used by the Factory to give the agent its "brain"
     */
    public void setStrategy(ITradingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * PHASE 2: The Observer "Push" method. 
     * The NewsBoard calls this when news happens.
     */
    @Override
    public void update(Event e) {
        examineEvent(e);
    }

    /**
     * Check if we actually own the stock mentioned in the news.
     */
    private void examineEvent(Event e) {
        // Safety check to ensure account exists
        if (exc.getAccounts().getTraderAccount(t) != null) {
            int positionInSecurity = exc.getAccounts().getTraderAccount(t).getPosition(e.getSecrity().getTicker());
            
                actOnEvent(e, positionInSecurity, exc.getPrice(e.getSecrity().getTicker()));
            
        }
    }

    /**
     * We leave this empty because we no longer "Pull" (poll) for news.
     */
        public void timeAdvancedTo(long time) {
        if (this.strategy != null) {
            // Just ask for the specific event at this time. 
            // No need to loop through tickers!
            Event e = news.getEventAt(time);
            if (e != null) {
                examineEvent(e);
            }
        }    
    }   
    

    /**
     * Instead of being abstract, we let the strategy handle the action.
     */
    protected void actOnEvent(Event e, int pos, int price) {
        if (this.strategy != null) {
            this.strategy.determineAction(e, this, pos, price);
        }
    }

	public void submitBid(Event e, int price, int volume) {
		IOrder newOrder = new Bid(t, e.getSecrity(), price, volume, e.getTime());
		exc.submitOrder(newOrder, e.getTime());
	}

	public void submitAsk(Event e, int price, int volume) {
		IOrder newOrder = new Ask(t, e.getSecrity(), price, volume, e.getTime());
		exc.submitOrder(newOrder, e.getTime());
	}
}