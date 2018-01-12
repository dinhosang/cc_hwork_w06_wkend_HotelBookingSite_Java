package LocationTest.HotelRoomsTest;

import Location.HotelRooms.BedType;
import Location.HotelRooms.Bedroom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BedroomTest {

    Bedroom bedroom;

    @Before
    public void before(){
        bedroom = new Bedroom("3", BedType.SINGLE, 5);
    }

    @Test
    public void canGetBedroomNumber(){
        assertEquals("3", bedroom.getRoomNumber());
    }

    @Test
    public void canGetBedroomCapacity(){
        assertEquals(1, bedroom.getCapacity());
    }

    @Test
    public void canGetRate(){
        assertEquals(5, bedroom.getRate());
    }

}
