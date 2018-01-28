package model.trade;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class TradeTest {

    @Test
    public void testTradeAmountCalc() throws Exception {
        final BigDecimal agreedFx = BigDecimal.valueOf(0.50);
        final BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
        final int units = 200;

        final Trade DummyTrade = new Trade(
                "AKD1",
                TradeExecute.BUY,
                LocalDate.of(2018, 1, 12),
                LocalDate.of(2018, 1, 15), // Its a Monday
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        agreedFx,
                        units,
                        pricePerUnit));

        // test initialization
        assertEquals(agreedFx, DummyTrade.getAgreedFx());
        assertEquals(pricePerUnit, DummyTrade.getPricePerUnit());
        assertEquals(units, DummyTrade.getUnits());

        final BigDecimal correct = pricePerUnit
                                    .multiply(agreedFx)
                                    .multiply(BigDecimal.valueOf(units))
                                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(correct, DummyTrade.getTradeAmount());
    }
}