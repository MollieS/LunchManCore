package LunchManCore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuestTest {

    @Test
    public void hasANameAndAnOrder() {
        Guest guest = new Guest("Tom", "Chicken");
        assertEquals("Tom", guest.getName());
        assertEquals("Chicken", guest.getOrder());
    }
}
