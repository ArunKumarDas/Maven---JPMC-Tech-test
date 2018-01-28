package business_logic;


import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import business_logic.stocksettle_days.DefultStockSettleDays;
import business_logic.stocksettle_days.GulfStockSettleDays;
import business_logic.stocksettle_days.StockSettleDays;
import model.trade.Trade;

/**
 * A settlement date calculator
 */
public class StockSettledDate  {

    /**
     * Helper function to calculate settlement date for every given trade
     * @param trades the trades of which the settlement date will be calculated
     */
    public static void calculateSettlementDates(Set<Trade> trade) {
        trade.forEach(StockSettledDate::calculateSettlementDate);
    }

    /**
     * Calculate the settlementDate Based on some logic
     * @param dummyTrade day is the day trade of which the settlement date will be calculated
     */
    public static <IStockSettleDays> void calculateSettlementDate(Trade dummyTrade) {
        // Select proper strategy based on the Currency
        final IStockSettleDays stockSettleDayMechanism =  (IStockSettleDays) getStockSettleDayStrategyy(dummyTrade.getCurrency());

        // find the correct settlement date
        final LocalDate newSettlementDate =
                 ((StockSettleDays) stockSettleDayMechanism).findFirstWorkingDate(dummyTrade.getSettlementDate());

        if (newSettlementDate != null) {
            // set the correct settlement date
            dummyTrade.setSettlementDate(newSettlementDate);
        }
    }

   

	/**
     * Select proper strategy based on the Currency
     * @param currency the currency to choose
     * @return the proper working days strategy
     */
    private static business_logic.stocksettle_days.IStockSettleDays  getStockSettleDayStrategyy(Currency currency) {
        if ((currency.getCurrencyCode().equals("AED")) ||
            (currency.getCurrencyCode().equals("SAR")))
        {
            return GulfStockSettleDays.getInstance();
        }
        return DefultStockSettleDays.getInstance();
    }
}
