package business_logic.stocksettle_days;



import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class DefultStockSettleDaysTest<stockSettleDays> {

    private IStockSettleDays stockSettleDays;
    @Before
    public void setUp() throws Exception {
        stockSettleDays = DefultStockSettleDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Monday() throws Exception {
        final LocalDate aMonday = LocalDate.of(2018, 1, 15);

        // should return the same, since Monday is a working by default
        assertEquals(aMonday, stockSettleDays.findFirstWorkingDate(aMonday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2018, 1, 19);

        // should return the same, since Friday is a working by default
        assertEquals(aFriday, stockSettleDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2018, 1, 20);

        // should return the first Monday (22/1/2018), since Saturday is not a working day
        assertEquals(LocalDate.of(2018, 1, 22), stockSettleDays.findFirstWorkingDate(aSaturday));
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2018, 1, 22);

        // should return the first Monday (22/1/2018), since Sunday is not a working day
        assertEquals(LocalDate.of(2018, 1, 22), stockSettleDays.findFirstWorkingDate(aSunday));
    }
}
