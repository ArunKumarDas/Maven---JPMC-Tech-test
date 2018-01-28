package business_logic.stocksettle_days;
import java.time.DayOfWeek;

public class DefultStockSettleDays  extends StockSettleDays {

    private static DefultStockSettleDays instance = null;

    public static DefultStockSettleDays getInstance() {
        if (instance == null) {
            instance = new DefultStockSettleDays();
        }
        return instance;
    }

    private DefultStockSettleDays() {
        super();
    }

    @Override
    protected void setupStockSettleDays() {
        this.isStockSettleDayMap.put(DayOfWeek.MONDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.TUESDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.THURSDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.FRIDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.SATURDAY, false);
        this.isStockSettleDayMap.put(DayOfWeek.SUNDAY, false);
    }
}
