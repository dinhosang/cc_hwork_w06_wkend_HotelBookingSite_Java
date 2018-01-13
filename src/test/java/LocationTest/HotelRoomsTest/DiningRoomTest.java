package LocationTest.HotelRoomsTest;

import Location.HotelRooms.DiningRoom;
import org.junit.Before;

public class DiningRoomTest {

    private DiningRoom diningRoom;

    @Before
    public void before(){
        diningRoom = new DiningRoom("The Food Hall", 15);
    }
}
