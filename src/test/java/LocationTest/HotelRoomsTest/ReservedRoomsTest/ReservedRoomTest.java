package LocationTest.HotelRoomsTest.ReservedRoomsTest;

import Location.HotelRooms.ReservedRooms.ReservedRoom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReservedRoomTest {

   private ReservedRoom room;

   @Before
    public void before(){
       room = new ReservedRoom("Rented Room",2, 8);
   }

    @Test
    public void canGetRate(){
        assertEquals(8, room.getRate());
    }
}
