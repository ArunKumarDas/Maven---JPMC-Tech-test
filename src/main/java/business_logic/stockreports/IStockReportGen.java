package business_logic.stockreports;

import java.util.Set;

import model.trade.Trade;


public interface IStockReportGen {
    
	String generateStockReportGen(Set<Trade> trade);

	

	

}

