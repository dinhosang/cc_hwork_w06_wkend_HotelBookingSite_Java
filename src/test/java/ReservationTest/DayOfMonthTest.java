package ReservationTest;

import Reservation.DayOfMonth;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayOfMonthTest {

    @Test
    public void canGetDate(){
        assertEquals(14, DayOfMonth.FOURTEENTH.getDate());
    }
}
