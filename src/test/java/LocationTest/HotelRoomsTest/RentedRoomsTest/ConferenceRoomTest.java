package LocationTest.HotelRoomsTest.RentedRoomsTest;

import Location.HotelRooms.RentedRooms.ConferenceRoom;
import org.junit.Before;
import org.junit.Test;

public class ConferenceRoomTest {

    ConferenceRoom conferenceRoom;

    @Before
    public void before(){
        String name = "The Alford Rood";
        int capacity = 10;
        int rate = 5;
        conferenceRoom = new ConferenceRoom(name, capacity, rate);
    }

}
