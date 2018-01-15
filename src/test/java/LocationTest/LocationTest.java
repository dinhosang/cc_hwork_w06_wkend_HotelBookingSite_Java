package LocationTest;

import Location.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    private Location room;

    @Before
    public void before(){
        room = new Location("The Room", 2);
    }

    @Test
    public void canGetRoomCapacity(){
        assertEquals(2, room.getCapacity());
    }

    @Test
    public void canGetName(){
        assertEquals("The Room", room.getName());
    }

}
