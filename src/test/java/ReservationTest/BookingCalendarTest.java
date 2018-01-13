package ReservationTest;

import Location.HotelRooms.ReservedRooms.BedType;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Person.Guest;
import Reservation.BookingCalendar;
import Reservation.ReservationResult;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class BookingCalendarTest {

    private BookingCalendar reservations;

    Guest guest1;
    Guest guest2;
    Bedroom bedroom;

    private LocalDate startDateReservation1;
    private LocalDate endDateReservation1;

    @Before
    public void before(){
        reservations = new BookingCalendar();

        guest1 = new Guest("Joanne", 300);
        guest2 = new Guest("Lyra", 250);
        bedroom = new Bedroom("4", BedType.DOUBLE, 15);

        startDateReservation1 = LocalDate.of(2017,1, 3);
        endDateReservation1 = LocalDate.of(2017, 1, 9);
    }

    @Test
    public void canAddBooking(){
        // guest to charge is used in a hotel method
        // check that guest can afford, then run addbooking
        // the if that returns true, charge guest

        // hotel controls first and third, this BookingCalendar method handles the middle
        assertEquals(ReservationResult.SUCCESS, reservations.addBooking(bedroom, startDateReservation1, endDateReservation1, guest1, guest2));
    }
}
