package business_logic.stocksettle_days;
import java.time.DayOfWeek;

public class GulfStockSettleDays extends StockSettleDays{

    private static GulfStockSettleDays instance = null;

    public static GulfStockSettleDays getInstance() {
        if (instance == null) {
            instance = new GulfStockSettleDays();
        }
        return instance;
    }

    private GulfStockSettleDays() {
        super();
    }

    @Override
    protected void setupStockSettleDays() {
        this.isStockSettleDayMap.put(DayOfWeek.SUNDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.MONDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.TUESDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.THURSDAY, true);
        this.isStockSettleDayMap.put(DayOfWeek.FRIDAY, false); // in UAE and Arab those are not working days
        this.isStockSettleDayMap.put(DayOfWeek.SATURDAY, false); // in UAE and Arab  those are not working days
    }
}