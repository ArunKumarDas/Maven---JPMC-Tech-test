package business_logic.stocksettle_days;

import java.time.LocalDate;

public interface IStockSettleDays  {
    LocalDate findFirstWorkingDate(LocalDate date);
}
