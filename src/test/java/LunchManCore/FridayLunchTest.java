package LunchManCore;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class FridayLunchTest {

    private FridayLunch fridayLunch;

    @Before
    public void setUp() {
        this.fridayLunch = new FridayLunch(LocalDate.of(2016, 6, 10));
    }

    @Test
    public void hasADate() {
        assertEquals(LocalDate.of(2016, 6, 10), fridayLunch.getDate());
    }

    @Test
    public void hasAnApprentice() {
        Apprentice nick = new Apprentice("Nick");
        fridayLunch.assignApprentice(nick);
        assertEquals(nick, fridayLunch.getApprentice().get());
    }

    @Test
    public void hasARestaurantAssigned() {
        String kinMenu = "http://www.kinstreetfood.com/Menu";
        Restaurant kin = new Restaurant("Kin", kinMenu);
        fridayLunch.assignRestaurant(kin);
        assertEquals("Kin", fridayLunch.getRestaurant().get().getName());
        assertEquals(kinMenu, fridayLunch.getRestaurant().get().getMenuLink());
    }

    @Test(expected = NonFridayException.class)
    public void rejectsNonFridayDates() {
        FridayLunch thursday = new FridayLunch(LocalDate.of(2016, 06, 9));
    }


}
