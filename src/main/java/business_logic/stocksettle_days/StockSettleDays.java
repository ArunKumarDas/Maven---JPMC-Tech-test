package business_logic.stocksettle_days;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class StockSettleDays implements IStockSettleDays {
    protected Map<DayOfWeek, Boolean> isStockSettleDayMap = new HashMap<DayOfWeek, Boolean>();

    protected abstract void setupStockSettleDays();

    public StockSettleDays() {
        setupStockSettleDays();
    }

    @Override
	public LocalDate findFirstWorkingDate(LocalDate date) {

        // for code safety, check if there is really an available weekday
        if (isStockSettleDayMap.values().stream().noneMatch(b -> b)) {
            return null;
        }

        // if there are available working days, then check for the first working day
        return findFirstWorkingDateRec(date);
    }

    private LocalDate findFirstWorkingDateRec(LocalDate date) {
        final DayOfWeek inputDay = date.getDayOfWeek();

        // in case the given date is working date, just return this
        if (isStockSettleDayMap.get(inputDay)) {
            return date;
        } else {
            // otherwise look for the next working date (Recursively)
            return findFirstWorkingDateRec(date.plusDays(1));
        }
    }
}
