package business_logic;

import business_logic.ranking.Rank;
import model.trade.Trade;
import model.trade.TradeDetails;
import model.trade.TradeExecute;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;


/**
 * Describes a mapping between dates and the trade amount of those dates, based on instructions
 */
public class StockSettleStatus {

    // Create a predicate for outgoing
    private final static Predicate<Trade> outgoingInstructionsPredicate =
           trade -> trade.getAction().equals(TradeExecute.BUY);

    // Create a predicate for incoming
    private final static Predicate<Trade> incomingInstructionsPredicate =
            trade -> trade.getAction().equals(TradeExecute.SELL);

    /**
     * Calculates the daily outgoing (BUY) trade amount in USD
     * @param trade the instruction to calculate the stock from
     * @return a map from date to total amount
     */
    public static Map<LocalDate, BigDecimal> calculateDailyOutgoingAmount(Set<Trade> trades) {
        return calculateDailyAmount(trades, outgoingInstructionsPredicate);
    }

    /**
     * Calculates the daily incoming (SELL) trade amount in USD
     * @param trade the instruction to calculate the stock from
     * @return a map from date to total amount
     */
    public static Map<LocalDate, BigDecimal> calculateDailyIncomingAmount(Set<Trade> trades) {
        return calculateDailyAmount(trades, incomingInstructionsPredicate);
    }

    /**
     * Ranks the daily outgoing (BUY) by trade amount in USD
     * @param trade the instruction to calculate the stock from
     * @return a map from date to a list of ranks (ranking)
     */
    public static Map<LocalDate, LinkedList<Rank>> calculateDailyOutgoingRanking(Set<Trade> trades) {
        return calculateRanking(trades, outgoingInstructionsPredicate);
    }

    /**
     * Ranks the daily incoming (SELL) by trade amount in USD
     * @param trade the instruction to calculate the stock from
     * @return a map from date to a list of ranks (ranking)
     */
    public static Map<LocalDate, LinkedList<Rank>> calculateDailyIncomingRanking(Set<Trade> trades) {
        return calculateRanking(trades, incomingInstructionsPredicate);
    }

    private static Map<LocalDate, BigDecimal> calculateDailyAmount(
            Set<Trade> trades, Predicate<Trade> predicate)
    {
        return trades.stream()
                .filter(predicate)
                .collect(groupingBy(Trade::getSettlementDate,
                    mapping(Trade::getTradeAmount,
                        reducing(BigDecimal.ZERO, BigDecimal::add))));
    }

    private static Map<LocalDate, LinkedList<Rank>> calculateRanking(
            Set<Trade> trades, Predicate<Trade> predicate)
    {
        final Map<LocalDate, LinkedList<Rank>> ranking = new HashMap<>();
          
        trades.stream()
            .filter(predicate)
            .collect(groupingBy(Trade::getSettlementDate, toSet()))
            .forEach((date, dailyInstructionSet) -> 
            {
                final AtomicInteger rank = new AtomicInteger(1);
               final LinkedList<Rank> ranks = dailyInstructionSet.stream()
                		   
            		        .sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
                		    .map(trade -> new Rank(rank.getAndIncrement(),trade.getEntity(),date))
                		    .collect(toCollection(LinkedList::new));   
                
                ranking.put(date, ranks);
            });

        return ranking;
    }
}
