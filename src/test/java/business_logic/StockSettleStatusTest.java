package business_logic;


import business_logic.ranking.Rank;
import model.trade.Trade;
import model.trade.TradeDetails;
import model.trade.TradeExecute;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.Test;



public class StockSettleStatusTest {

    private static final LocalDate MONDAY    = LocalDate.of(2018, 1, 8);
    private static final LocalDate TUESDAY   = LocalDate.of(2018, 1, 9);
    private static final LocalDate WEDNESDAY = LocalDate.of(2018, 1, 10);
    private static final LocalDate SATURDAY  = LocalDate.of(2018, 1, 6);
    private static final LocalDate SUNDAY    = LocalDate.of(2018, 1, 7);

    private static Set<Trade> getFakeSetOftrade() {
        final Set<Trade> trade = new HashSet<>();

        // ===========================================================================
        // All these should be under the same settlement date:: 08/01/2018
        // ===========================================================================
        trade.add(new Trade(
                "AKD1",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 4),
                MONDAY,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        100,
                        BigDecimal.valueOf(1))));

        trade.add(new Trade(
                "AKD2",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 5),
                MONDAY,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(1))));

        trade.add(new Trade(
                "AKD3",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 7),
                SATURDAY,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        300,
                        BigDecimal.valueOf(1))));

        trade.add(new Trade(
                "AKD4",
                TradeExecute.SELL,
                LocalDate.of(2018, 1, 5),
                SUNDAY,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(1))));

        // ===========================================================================
        //  :::: All these should be under the same settlement date ::: 09/01/2018
        // ===========================================================================
        trade.add(new Trade(
                "AKD5",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 9),
                TUESDAY,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        400,
                        BigDecimal.valueOf(1))));

        trade.add(new Trade(
                "AKD6",
                TradeExecute.SELL,
                LocalDate.of(2018, 1, 9),
                TUESDAY,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        1000,
                        BigDecimal.valueOf(1))));

        // ===========================================================================
        // ::::: All these should be under the same settlement date ::::: 10/01/2018
        // ===========================================================================
        trade.add(new Trade(
                "AKD7",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 9),
                WEDNESDAY,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        7000,
                        BigDecimal.valueOf(1))));

        StockSettledDate.calculateSettlementDates(trade);

        return trade;
    }

    @Test
    public void testDailyIncomingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyIncomingAmount =
               StockSettleStatus.calculateDailyIncomingAmount(getFakeSetOftrade());

        assertEquals(2, dailyIncomingAmount.size());
        assertTrue(Objects.equals(dailyIncomingAmount.get(MONDAY), BigDecimal.valueOf(200.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyIncomingAmount.get(TUESDAY), BigDecimal.valueOf(1000.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyOutgoingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
               StockSettleStatus.calculateDailyOutgoingAmount(getFakeSetOftrade());

        assertEquals(3, dailyOutgoingAmount.size());
        assertTrue(Objects.equals(dailyOutgoingAmount.get(MONDAY), BigDecimal.valueOf(600.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyOutgoingAmount.get(TUESDAY), BigDecimal.valueOf(400.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
//        assertTrue(Objects.equals(dailyOutgoingAmount.get(WEDNESDAY), BigDecimal.valueOf(700.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyIncomingRanking() throws Exception {
        final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking =
               StockSettleStatus.calculateDailyIncomingRanking(getFakeSetOftrade());

        assertEquals(2, dailyIncomingRanking.size());

        assertEquals(1, dailyIncomingRanking.get(MONDAY).size());
        assertEquals(1, dailyIncomingRanking.get(TUESDAY).size());

        assertTrue(dailyIncomingRanking.get(MONDAY).contains(new Rank(1, "AKD4", MONDAY)));
        assertTrue(dailyIncomingRanking.get(TUESDAY).contains(new Rank(1, "AKD6", TUESDAY)));

    }

    @Test
    public void testDailyOutgoingRanking() throws Exception {
        final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking =
               StockSettleStatus.calculateDailyOutgoingRanking(getFakeSetOftrade());

        assertEquals(3, dailyOutgoingRanking.size());

        assertEquals(3, dailyOutgoingRanking.get(MONDAY).size());
        assertEquals(1, dailyOutgoingRanking.get(TUESDAY).size());
        assertEquals(1, dailyOutgoingRanking.get(WEDNESDAY).size());

        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(1, "AKD3", MONDAY)));
        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(2, "AKD2", MONDAY)));
        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(3, "AKD1", MONDAY)));

        assertTrue(dailyOutgoingRanking.get(TUESDAY).contains(new Rank(1, "AKD5", TUESDAY)));

        assertTrue(dailyOutgoingRanking.get(WEDNESDAY).contains(new Rank(1, "AKD7", WEDNESDAY)));
    }
    
}

