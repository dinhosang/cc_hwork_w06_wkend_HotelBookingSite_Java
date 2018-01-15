package ReservationTest;

import Location.Helper.CreateHashMapOfRooms;
import Location.Hotel.Hotel;
import Location.HotelRooms.ReservedRooms.BedType;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Location.HotelRooms.ReservedRooms.ConferenceRoom;
import Location.HotelRooms.ReservedRooms.ReservedRoom;
import Location.HotelRooms.RoomType;
import Person.Guest;
import Reservation.Booking;
import Reservation.BookingCalendar;
import Reservation.ReservationResult;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BookingCalendarTest {

    private BookingCalendar reservations;

    private Guest guest1;
    private Guest guest2;
    private Bedroom bedroom;
    private ConferenceRoom conferenceRoom;
    private Hotel hotel;
    private long cost;

    private LocalDate startDateReservation1;
    private LocalDate endDateReservation1;

    @Before
    public void before(){
        reservations = new BookingCalendar();

        guest1 = new Guest("JoJo","Joanne", "Ashcroft", 300);
        guest2 = new Guest("Liar","Lyra", "Possti", 250);
        bedroom = new Bedroom("4", BedType.DOUBLE, 15);

        hotel = new Hotel("Testing", 10);

        cost = 10;

        startDateReservation1 = LocalDate.of(2017,1, 3);
        endDateReservation1 = LocalDate.of(2017, 1, 9);
    }

    @Test
    public void canAddBookingToCalendar(){
        assertEquals(ReservationResult.SUCCESS, reservations.addReservation(hotel, bedroom, cost, startDateReservation1, endDateReservation1, guest1, guest1.getName(), guest2.getName()));
    }

    @Test
    public void canFindRoomsBookedOnSpecificDate(){
        reservations.addReservation(hotel, bedroom, cost, startDateReservation1, endDateReservation1, guest1, guest1.getName(), guest2.getName());

        HashMap<RoomType, ArrayList> bookedRooms = reservations.findBookedRoomsDateRange(startDateReservation1, startDateReservation1);

        assertEquals(true, bookedRooms.containsKey(RoomType.BEDROOM));
        assertEquals(false, bookedRooms.containsKey(RoomType.DININGROOM));
        assertEquals(1, bookedRooms.get(RoomType.BEDROOM).size());
        assertEquals(true, bookedRooms.get(RoomType.CONFERENCEROOM).isEmpty());
        assertNotEquals(2, bookedRooms.get(RoomType.BEDROOM).size());
    }


}
