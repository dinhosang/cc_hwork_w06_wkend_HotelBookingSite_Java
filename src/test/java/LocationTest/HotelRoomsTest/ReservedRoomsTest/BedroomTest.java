package LocationTest.HotelRoomsTest.ReservedRoomsTest;

import Location.HotelRooms.ReservedRooms.BedType;
import Location.HotelRooms.ReservedRooms.Bedroom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BedroomTest {

    private Bedroom bedroom;

    @Before
    public void before(){
        bedroom = new Bedroom("3", BedType.SINGLE, 5);
    }

    @Test
    public void canGetBedroomNumber(){
        assertEquals("Room 3", bedroom.getRoomNumber());
    }

    @Test
    public void canGetBedroomCapacity(){
        assertEquals(1, bedroom.getCapacity());
    }

}
