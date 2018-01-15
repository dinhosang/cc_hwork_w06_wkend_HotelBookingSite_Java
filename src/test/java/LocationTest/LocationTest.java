package LocationTest;

import Location.Location;
import Person.Guest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    private Location room;
    private Guest guest1;
    private Guest guest2;
    private Guest guest3;

    @Before
    public void before(){
        room = new Location("The Room", 2);
        guest1 = new Guest("Flava","Flavia", "Maury", 20);
        guest2 = new Guest("DeDe","Duncan", "Jacques", 40);
        guest3 = new Guest("Lala","Leira", "Woodrow", 80);
    }

    @Test
    public void checkOccupancyDefaultsToEmpty(){
        assertEquals(true, room.getOccupants().isEmpty());
    }

    @Test
    public void canGetRoomCapacity(){
        assertEquals(2, room.getCapacity());
    }

    @Test
    public void canReceiveGuests(){
        room.receiveGuests(guest1, guest2);
        assertEquals(2, room.getOccupants().size());
        assertEquals(true, room.getOccupants().contains(guest2));
    }

    @Test
    public void canReleaseGuests(){
        room.receiveGuests(guest1, guest3, guest2);
        room.releaseGuests(guest2, guest1);
        assertEquals(1, room.getOccupants().size());
        assertEquals(true, room.getOccupants().contains(guest3));
    }

    @Test
    public void canGetName(){
        assertEquals("The Room", room.getName());
    }

}
