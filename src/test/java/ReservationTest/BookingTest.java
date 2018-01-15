package ReservationTest;

import Location.Hotel.Hotel;
import Location.HotelRooms.ReservedRooms.BedType;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Location.HotelRooms.RoomType;
import Reservation.Booking;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class BookingTest {

    private Hotel hotel;
    private Bedroom bedroom;
    private LocalDate arrivalDate;
    private LocalDate leaveDate;
    private ArrayList<String> guests;
    private long cost;
    private Booking booking;

    @Before
    public void before(){
        hotel = new Hotel("Testing Hotels", 10);
        bedroom = new Bedroom("4", BedType.DOUBLE, 5);
        arrivalDate = LocalDate.of(2017, 12, 29);
        leaveDate = LocalDate.of(2018, 1, 5);
        guests = new ArrayList<>(Arrays.asList("Zoe", "Jim"));
        cost = 100;

        booking = new Booking(hotel, bedroom, cost, arrivalDate, leaveDate, guests);
    }

    @Test
    public void canGetHotelFromBooking(){
        assertEquals(hotel, booking.getHotel());
    }

    @Test
    public void canGetRoomFromBooking(){
        assertEquals(bedroom, booking.getRoom());
    }

    @Test
    public void canGetArrivalDateFromBooking(){
        assertEquals(arrivalDate, booking.getArrivalDate());
    }

    @Test
    public void canGetLeaveDateFromBooking(){
        assertEquals(leaveDate, booking.getLeaveDate());
    }

    @Test
    public void canGetArrayListGuestNamesFromBooking(){
        assertEquals(guests, booking.getGuests());
    }

    @Test
    public void canGetCostFromBooking(){
        assertEquals(cost, booking.getCost());
    }
}
