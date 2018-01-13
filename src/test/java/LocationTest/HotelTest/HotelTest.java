package LocationTest.HotelTest;

import Location.Location;
import Location.Hotel.Hotel;
import Location.HotelRooms.DiningRoom;
import Location.HotelRooms.ReservedRooms.BedType;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Location.HotelRooms.ReservedRooms.ConferenceRoom;
import Person.Guest;
import Reservation.ReservationResult;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class HotelTest {

    private Hotel hotel;
    private Bedroom bedroom1;
    private Bedroom bedroom2;
    private Bedroom bedroom3;
    private ConferenceRoom conferenceRoomSmall;
    private ConferenceRoom conferenceRoomBig;
    private DiningRoom diningRoom;
    private Guest guest1;
    private Guest guest2;
    private LocalDate startDate;
    private LocalDate endDate;


    @Before
    public void before(){
        bedroom1 = new Bedroom("1", BedType.SINGLE, 5);
        bedroom2 = new Bedroom("2", BedType.TRIPLE, 18);
        bedroom3 = new Bedroom("3", BedType.DOUBLE, 10);

        conferenceRoomSmall = new ConferenceRoom("First Conference", 10, 10);
        conferenceRoomBig = new ConferenceRoom("Second Conference Room", 30, 20);


        diningRoom = new DiningRoom("Fine Cuisine", 40);


        hotel = new Hotel("Code el California", 20);

        guest1 = new Guest("Liara", 100);
        guest2 = new Guest("Jacob", 85);

        startDate = LocalDate.of(2018,6,28);
        endDate = LocalDate.of(2018, 7, 4);
    }

    @Test
    public void canAddRooms(){
        hotel.addRooms(bedroom1, bedroom2, conferenceRoomSmall, diningRoom, bedroom3, conferenceRoomBig);
        assertEquals(6, hotel.numberOfRooms());
        assertEquals(true, hotel.containsRoom(conferenceRoomSmall));
    }

    @Test
    public void canGetCapacity(){
        hotel.addRooms(bedroom1, bedroom2, conferenceRoomSmall);
        assertEquals(34, hotel.getCapacity());
    }

    @Test
    public void canRemoveRooms(){
        hotel.addRooms(bedroom1, conferenceRoomSmall, conferenceRoomBig, bedroom3, bedroom2);
        hotel.removeRooms(bedroom3, conferenceRoomSmall);
        assertEquals(3, hotel.numberOfRooms());
        assertEquals(false, hotel.containsRoom(conferenceRoomSmall));
        assertEquals(false, hotel.containsRoom(bedroom3));
        assertEquals(54, hotel.getCapacity());
    }

    @Test
    public void canReceiveReservationRequestSuccess(){
        hotel.addRooms(bedroom3);

        assertEquals(ReservationResult.SUCCESS, hotel.receiveReservationRequest(bedroom3, startDate, endDate, guest1, guest1, guest2));
    }

}
