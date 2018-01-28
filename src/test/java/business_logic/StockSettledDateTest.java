package business_logic;

import static org.junit.Assert.*;
import model.trade.Trade;
import model.trade.TradeDetails;
import model.trade.TradeExecute;
import business_logic.StockSettledDate;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class StockSettledDateTest {
    @Test
    public void calculateSettlementDate_default_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 1, 12); // Its a Friday

        final Trade DummyTrade = new Trade(
                "AKD1",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 10),
                initialSettlementDate,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        
        StockSettledDate.calculateSettlementDate(DummyTrade);

        // should be the same
        assertEquals(initialSettlementDate, DummyTrade.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_default_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 1, 14); // Its a Sunday

        final Trade DummyTrade = new Trade(
                "AKD1",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 10),
                initialSettlementDate,
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        300,
                        BigDecimal.valueOf(120.25)));

        // calculate new settlement day
        StockSettledDate.calculateSettlementDate(DummyTrade);

        // should be the first monday (15/01/2018)
        assertEquals(LocalDate.of(2018, 1, 15), DummyTrade.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 01, 12); // Its a Friday

        final Trade DummyTrade = new Trade(
                "AKD1",
                TradeExecute.BUY,
                LocalDate.of(2018, 01, 10),
                initialSettlementDate,
                new TradeDetails(
                        Currency.getInstance("AED"), // Its Arabia (AED)
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        StockSettledDate.calculateSettlementDate(DummyTrade);

        // should be the first Sunday (14/1/2018)
        assertEquals(LocalDate.of(2018, 1, 14), DummyTrade.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 1, 14); // Its a Sunday

        final Trade DummyTrade = new Trade(
                "AKD1",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 10),
                initialSettlementDate,
                new TradeDetails(
                        Currency.getInstance("SAR"), // Its Arabia (SAR)
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        StockSettledDate.calculateSettlementDate(DummyTrade);

        // should be the same
        assertEquals(initialSettlementDate, DummyTrade.getSettlementDate());
    }
}
