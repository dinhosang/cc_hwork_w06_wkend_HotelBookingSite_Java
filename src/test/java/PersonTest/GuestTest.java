package PersonTest;

import Location.Hotel.Hotel;
import Location.HotelRooms.ReservedRooms.BedType;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Location.HotelRooms.RoomType;
import Person.Guest;
import Reservation.ReservationResult;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class GuestTest {

    private Guest guest1;
    private Guest guest2;
    private Guest guest3;
    private Hotel hotel;
    private Bedroom bedroom;
    private LocalDate startDate;
    private LocalDate endDate;

    @Before
    public void before(){
        guest1 = new Guest("Keira", 100);
        guest2 = new Guest("Flavia", 200);
        guest3 = new Guest("Rachael", 300);

        bedroom = new Bedroom("4", BedType.DOUBLE, 5);

        hotel = new Hotel("Hot Hotel", 10);
        hotel.addRooms(bedroom);

        startDate = LocalDate.of(2019, 1, 4);
        endDate = LocalDate.of(2019, 1, 14);
    }

    @Test
    public void canGetName(){
        assertEquals("Keira", guest1.getName());
    }

    @Test
    public void canGetWaller(){
        assertEquals(100, guest1.getWallet());
    }

    @Test
    public void canModifyWaller(){
        guest1.spendMoney(20);
        assertEquals(80, guest1.getWallet());
    }

    @Test
    public void canSuccessfullyMakeReservation(){
        assertEquals(ReservationResult.SUCCESS, guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1, guest2));
        assertEquals(50, guest1.getWallet());
    }

    @Test
    public void reservationFailureFundsTooLow(){
        guest1.spendMoney(80);
        assertEquals(ReservationResult.FAILEDNOTENOUGHFUNDS, guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1, guest2));
    }

    @Test
    public void reservationFailureRoomAlreadyReserved(){
        guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1, guest2);

        assertEquals(ReservationResult.FAILEDALREADYRESERVED, guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1, guest2));
    }

    @Test
    public void reservationFailureRoomTooSmall(){
        assertEquals(ReservationResult.FAILEDCAPACITY, guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1, guest2, guest3));
    }
}
