package LocationTest.HotelRoomsTest.RentedRoomsTest;

import Location.HotelRooms.RentedRooms.BedType;
import Location.HotelRooms.RentedRooms.Bedroom;
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
        assertEquals("3", bedroom.getRoomNumber());
    }

    @Test
    public void canGetBedroomCapacity(){
        assertEquals(1, bedroom.getCapacity());
    }

}
