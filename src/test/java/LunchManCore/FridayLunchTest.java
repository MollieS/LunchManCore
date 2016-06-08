package LunchManCore;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class FridayLunchTest {

    @Test
    public void hasADate() {
        FridayLunch fridayLunch = new FridayLunch(LocalDate.of(2016, 6, 10));
        assertEquals(LocalDate.of(2016, 6, 10), fridayLunch.getDate());
    }

    @Test
    public void hasAnApprentice() {
        FridayLunch fridayLunch = new FridayLunch(LocalDate.of(2016, 6, 10));
        Apprentice nick = new Apprentice("Nick");
        fridayLunch.assignApprentice(nick);
        assertEquals(nick, fridayLunch.getApprentice().get());
    }

}
