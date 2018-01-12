package LocationTest.HotelRoomsTest.RentedRoomsTest;

import Location.HotelRooms.RentedRooms.RentedRoom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RentedRoomTest {

   RentedRoom room;

   @Before
    public void before(){
       room = new RentedRoom("Rented Room",2, 8);
   }

    @Test
    public void canGetRate(){
        assertEquals(8, room.getRate());
    }
}
