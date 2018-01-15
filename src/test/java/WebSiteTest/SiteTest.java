package WebSiteTest;

import WebSite.Site;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SiteTest {

    private Site site;


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
