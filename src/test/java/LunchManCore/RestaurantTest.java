package LunchManCore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RestaurantTest {

    @Test
    public void hasAName() {
        Restaurant kin = new Restaurant("Kin", "http://www.kinstreetfood.com/Menu");
        assertEquals("Kin", kin.getName());
    }

    @Test
    public void hasAMenuLink() {
        String kinMenu = "http://www.kinstreetfood.com/Menu";
        Restaurant kin = new Restaurant("Kin", "http://www.kinstreetfood.com/Menu");
        assertEquals(kinMenu, kin.getMenuLink());
    }

}
