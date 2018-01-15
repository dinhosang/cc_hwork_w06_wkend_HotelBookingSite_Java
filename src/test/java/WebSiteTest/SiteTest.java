package WebSiteTest;

import Person.Guest;
import WebSite.Site;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SiteTest {

    Site site;
    Guest user;

    @Before
    public void before(){
        this.site = new Site("the site");
    }

    @Test
    public void canFindAccount(){
        this.site.addUser("handle", "Janes", "Foster", 200);
        assertEquals(1, site.getUserAccounts().size());
    }


}
