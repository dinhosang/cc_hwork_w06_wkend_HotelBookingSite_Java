package PersonTest;

import Location.Hotel.Hotel;
import Location.HotelRooms.DiningRoom;
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
    private DiningRoom diningRoom;
    private LocalDate startDate;
    private LocalDate endDate;

    @Before
    public void before(){
        guest1 = new Guest("Key","Keira", "Metz", 100);
        guest2 = new Guest("Flow","Flavia", "Rossi", 200);
        guest3 = new Guest("Rocket","Rachael", "Mears", 300);

        bedroom = new Bedroom("4", BedType.DOUBLE, 5);

        diningRoom = new DiningRoom("The Room", 10);

        hotel = new Hotel("Hot Hotel", 10);
        hotel.addRooms(bedroom);

        startDate = LocalDate.of(2019, 1, 4);
        endDate = LocalDate.of(2019, 1, 14);
    }

    @Test
    public void canGetName(){
        assertEquals("Keira Metz", guest1.getName());
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
        assertEquals(ReservationResult.SUCCESS, guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1.getName(), guest2.getName()));
        assertEquals(50, guest1.getWallet());
    }

    @Test
    public void reservationFailureFundsTooLow(){
        guest1.spendMoney(80);
        assertEquals(ReservationResult.FAILEDNOTENOUGHFUNDS, guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1.getName(), guest2.getName()));
    }

    @Test
    public void reservationFailureRoomAlreadyReserved(){
        guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1.getName(), guest2.getName());

        assertEquals(ReservationResult.FAILEDALREADYRESERVED, guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1.getName(), guest2.getName()));
    }

    @Test
    public void reservationFailureRoomTooSmall(){
        assertEquals(ReservationResult.FAILEDCAPACITY, guest1.requestReservation(hotel, bedroom, startDate, endDate, guest1.getName(), guest2.getName(), guest3.getName()));
    }

    @Test
    public void reservationFailureRoomWrongType(){
        assertEquals(ReservationResult.FAILEDWRONGROOMTYPE, guest1.requestReservation(hotel, diningRoom, startDate, endDate, guest1.getName(), guest2.getName(), guest3.getName()));
    }

    @Test
    public void reservationFailureEndDateBeforeStartDate(){
        assertEquals(ReservationResult.FAILEDENDDATEISBEFORESTARTDATE, guest1.requestReservation(hotel, bedroom, endDate, startDate, guest1.getName(), guest2.getName()));
    }
}