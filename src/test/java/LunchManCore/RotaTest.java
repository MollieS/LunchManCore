package LunchManCore;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class RotaTest {

    @Test
    public void canAssignOneApprenticeToAFridayLunch() {
        Rota rota = new Rota(5, new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        FridayLunch nextFriday = new FridayLunch(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        Apprentice ced = new Apprentice("Cedric");
        rota.assign(nextFriday, ced);
        assertEquals(ced, rota.getSchedule().get(0).getApprentice().get());
    }

    @Test
    public void canAssignOneApprenticeToFourFridayLunches() {
        Rota rota = new Rota(5, new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        Apprentice priya = new Apprentice("Priya");
        List<Apprentice> apprentices = Arrays.asList(priya);
        rota.updateSchedule(apprentices);
        for (FridayLunch fridayLunch : rota.getSchedule()) {
            assertEquals(priya, fridayLunch.getApprentice().get());
        }
    }

    @Test
    public void knowsWhenTheNextFridayIsWhenScheduleHasData() {
        Rota rota = new Rota(5, new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        FridayLunch nextFriday = new FridayLunch(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        Apprentice ced = new Apprentice("Cedric");
        rota.assign(nextFriday, ced);
        Apprentice priya = new Apprentice("Priya");
        List<Apprentice> apprentices = Arrays.asList(priya);
        rota.updateSchedule(apprentices);
        assertEquals(new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()), rota.getSchedule().get(1).getDate());
    }

    @Test
    public void knowsWhenTheNextFridayIsWithEmptySchedule() {
        Rota rota = new Rota(5, new Date(Timestamp.valueOf("2016-06-09 07:00:00").getTime()));
        Apprentice priya = new Apprentice("Priya");
        List<Apprentice> apprentices = Arrays.asList(priya);
        rota.updateSchedule(apprentices);
        assertEquals(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()), rota.getSchedule().get(1).getDate());
    }

    @Test
    public void knowsWhenTheNextFridayFromAFriday() {
        Rota rota = new Rota(5, new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        Apprentice priya = new Apprentice("Priya");
        List<Apprentice> apprentices = Arrays.asList(priya);
        rota.updateSchedule(apprentices);
        assertEquals(new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()), rota.getSchedule().get(1).getDate());
    }
}
