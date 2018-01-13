package ReservationTest;

import Reservation.Month;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MonthTest {

    @Test
    public void canGetMaxDays(){
        assertEquals(29, Month.FEBRUARY.getMaxDays());
    }
}
