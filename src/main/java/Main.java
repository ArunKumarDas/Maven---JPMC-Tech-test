
import model.trade.Trade;

import business_logic.stockreports.IStockReportGen;
import business_logic.stockreports.StockReportGen;
import utils.DummyTradeGenerator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final Set<Trade> trade = DummyTradeGenerator.getDummyTrade();
        final IStockReportGen stockReportGen = new StockReportGen();

        System.out.println(stockReportGen.generateStockReportGen(trade));
    }
}
