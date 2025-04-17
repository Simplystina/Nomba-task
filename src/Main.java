import java.util.*;

public class Main {

    public static class Trade {
        public enum Type { BUY, SELL }

        Type type;
        double amount;
        double rate;

        public Trade(Type type, double amount, double rate) {
            this.type = type;
            this.amount = amount;
            this.rate = rate;
        }

        @Override
        public String toString() {
            return type + " " + amount + " @ " + rate;
        }
    }

    public static class Result {
        double totalSpreadRevenue;
        List<Trade> unmatchedTrades;

        public Result(double revenue, List<Trade> unmatched) {
            this.totalSpreadRevenue = revenue;
            this.unmatchedTrades = unmatched;
        }
    }

    public static Result analyzeTrades(List<Trade> trades) {
        Queue<Trade> buyQueue = new LinkedList<>();
        double revenue = 0.0;

        for (Trade trade : trades) {
            if (trade.type == Trade.Type.BUY) {
                buyQueue.add(new Trade(trade.type, trade.amount, trade.rate));
            } else { 
                //If the trade is a sell, find a matching buy
                double sellAmount = trade.amount;
                while (sellAmount > 0 && !buyQueue.isEmpty()) {
                    Trade buy = buyQueue.peek();
                    double matchedAmount = Math.min(sellAmount, buy.amount);
                    revenue += matchedAmount * (trade.rate - buy.rate);
                    sellAmount -= matchedAmount;
                    buy.amount -= matchedAmount;

                    if (buy.amount == 0) buyQueue.poll(); 
                }
            }
        }

        return new Result(revenue, new ArrayList<>(buyQueue));
    }

    
    public static void main(String[] args) {
        List<Trade> trades = List.of(
            new Trade(Trade.Type.BUY, 100000, 1.095),
            new Trade(Trade.Type.BUY, 50000, 1.100),
            new Trade(Trade.Type.SELL, 120000, 1.105)
        );

        Result result = analyzeTrades(trades);

        System.out.printf("The Total Spread Revenue is: %.2f\n", result.totalSpreadRevenue);
        System.out.println("The Unmatched Trades include:");
        result.unmatchedTrades.forEach(trade -> System.out.println(trade));

    }
}
