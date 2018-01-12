package LocationTest.HotelRoomsTest;

import Location.HotelRooms.BedType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BedTypeTest {

    @Test
    public void canGetCapacityOfBedType(){
        assertEquals(2, BedType.DOUBLE.getTypeCapacity());
    }
}
