package LunchManCore;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class FridayLunchTest {

    @Test
    public void hasADate() {
        FridayLunch fridayLunch = new FridayLunch(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        assertEquals(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()), fridayLunch.getDate());
    }

    @Test
    public void hasAnApprentice() {
        FridayLunch fridayLunch = new FridayLunch(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        Apprentice nick = new Apprentice("Nick");
        fridayLunch.assignApprentice(nick);
        assertEquals(nick, fridayLunch.getApprentice().get());
    }

}
