package PersonTest;

import Person.Guest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuestTest {

    Guest guest;

    @Before
    public void before(){
        guest = new Guest("Keira", 100);
    }

    @Test
    public void canGetName(){
        assertEquals("Keira", guest.getName());
    }

    @Test
    public void canGetWaller(){
        assertEquals(100, guest.getWallet());
    }
}
