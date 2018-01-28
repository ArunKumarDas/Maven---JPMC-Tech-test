package business_logic.stocksettle_days;

import static org.junit.Assert.*;

import business_logic.stocksettle_days.StockSettleDays;
import org.junit.Before;
import org.junit.Test;





import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class GulfStockSettleDaysTest {
    private IStockSettleDays stocksettleDays;
   
    @Before
    public void setUp() throws Exception {
    stocksettleDays = GulfStockSettleDaysTest.getInstance();
    }

    private static IStockSettleDays getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2018, 1, 14);

        // should return the same, since Sunday is a working day in Arabia
        assertEquals(aSunday, stocksettleDays.findFirstWorkingDate(aSunday));
    }

    @Test
    public void testFindFirstWorkingDate_Thursday() throws Exception {
        final LocalDate aThursday = LocalDate.of(2018, 1, 18);

        // should return the same, since Thursday is a working day in Arabia
        assertEquals(aThursday, stocksettleDays.findFirstWorkingDate(aThursday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2018, 1, 19);

        // should return the first Sunday (21/1/2018), since Friday is not a working day
        assertEquals(LocalDate.of(2018, 1, 19), stocksettleDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2018, 1, 20);

        // should return the first Monday (22/1/2018), since Saturday is not a working day
        assertEquals(LocalDate.of(2018, 1, 20), stocksettleDays.findFirstWorkingDate(aSaturday));
    }

}

