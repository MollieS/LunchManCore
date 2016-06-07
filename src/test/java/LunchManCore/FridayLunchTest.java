package LunchManCore;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class FridayLunchTest {

    @Test
    public void hasADate() {
        FridayLunch fridayLunch = new FridayLunch(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        assertEquals(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()), fridayLunch.getDate());
    }
}
