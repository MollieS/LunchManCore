package LunchManCore;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class FridayLunchTest {

    @Test
    public void hasADate() {
        FridayLunch fridayLunch = new FridayLunch(new Date(20160611));
        assertEquals(new Date(20160611), fridayLunch.getDate());
    }
}
