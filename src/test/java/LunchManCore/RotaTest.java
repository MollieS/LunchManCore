package LunchManCore;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class RotaTest {

    private Rota rota;

    @Before
    public void setUp() {
        this.rota = new Rota(4);
    }

    @Test
    public void canAssignOneApprenticeToAFridayLunch() {
        FridayLunch nextFriday = new FridayLunch(new Date(Timestamp.valueOf("2016-06-10 07:00:00").getTime()));
        Apprentice ced = new Apprentice("Cedric");
        rota.assign(nextFriday, ced);
        assertEquals(ced, rota.getSchedule().get(0).getApprentice().get());
    }


    @Test
    public void findsDateOfNextFridayWhenScheduleIsEmpty() {
        Date date = new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime());
        assertEquals(date, rota.findNextFriday(new Date(Timestamp.valueOf("2016-06-11 07:00:00").getTime())));
    }

    @Test
    public void findsTheNextFourFridaysWhenScheduleIsEmpty() {
        List<Date> result = rota.findNextFridays(new Date(Timestamp.valueOf("2016-06-11 07:00:00").getTime()));
        assertEquals(new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()), result.get(0));
        assertEquals(new Date(Timestamp.valueOf("2016-06-24 07:00:00").getTime()), result.get(1));
        assertEquals(new Date(Timestamp.valueOf("2016-07-01 07:00:00").getTime()), result.get(2));
        assertEquals(new Date(Timestamp.valueOf("2016-07-08 07:00:00").getTime()), result.get(3));
    }

    @Test
    public void createsTheNextFourFridayLunchesWhenScheduleIsEmpty() {
        List<FridayLunch> result = rota.createFridays(new Date(Timestamp.valueOf("2016-06-11 07:00:00").getTime()));
        assertEquals(new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()), result.get(0).getDate());
        assertEquals(new Date(Timestamp.valueOf("2016-06-24 07:00:00").getTime()), result.get(1).getDate());
        assertEquals(new Date(Timestamp.valueOf("2016-07-01 07:00:00").getTime()), result.get(2).getDate());
        assertEquals(new Date(Timestamp.valueOf("2016-07-08 07:00:00").getTime()), result.get(3).getDate());
    }

    @Test
    public void createsTheNext3FridayLunchesWhenScheduleHasOneFriday() {
        FridayLunch nextFriday = new FridayLunch(new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()));
        Apprentice ced = new Apprentice("Cedric");
        rota.assign(nextFriday, ced);
        List<FridayLunch> result = rota.createFridays(new Date(Timestamp.valueOf("2016-06-15 07:00:00").getTime()));
        assertEquals(new Date(Timestamp.valueOf("2016-06-24 07:00:00").getTime()), result.get(0).getDate());
        assertEquals(new Date(Timestamp.valueOf("2016-07-01 07:00:00").getTime()), result.get(1).getDate());
        assertEquals(new Date(Timestamp.valueOf("2016-07-08 07:00:00").getTime()), result.get(2).getDate());
        assertEquals(3, result.size());
    }

    @Test
    public void canAssignOneApprenticeToFourFridayLunches() {
        Apprentice priya = new Apprentice("Priya");
        List<Apprentice> apprentices = new LinkedList<>(Arrays.asList(priya));
        rota.updateSchedule(apprentices, new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()));
        List<FridayLunch> result = rota.getSchedule();
        assertEquals(priya, result.get(0).getApprentice().get());
        assertEquals(priya, result.get(1).getApprentice().get());
        assertEquals(priya, result.get(2).getApprentice().get());
        assertEquals(priya, result.get(3).getApprentice().get());
    }

    @Test
    public void canAssignFourApprenticesToFourFridayLunches() {
        Apprentice priya = new Apprentice("Priya");
        Apprentice ced = new Apprentice("Ced");
        Apprentice nick = new Apprentice("Nick");
        Apprentice mollie = new Apprentice("Mollie");
        List<Apprentice> apprentices = new LinkedList<>(Arrays.asList(priya, ced, nick, mollie));
        rota.updateSchedule(apprentices, new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()));
        List<FridayLunch> result = rota.getSchedule();
        assertEquals(priya, result.get(0).getApprentice().get());
        assertEquals(ced, result.get(1).getApprentice().get());
        assertEquals(nick, result.get(2).getApprentice().get());
        assertEquals(mollie, result.get(3).getApprentice().get());
    }

    @Test
    public void canAssignTwoApprenticesToThreeFridayLunches() {
        Apprentice nick = new Apprentice("Nick");
        Apprentice mollie = new Apprentice("Mollie");
        List<Apprentice> apprentices = new LinkedList<>(Arrays.asList(nick, mollie));
        FridayLunch nextFriday = new FridayLunch(new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()));
        Apprentice ced = new Apprentice("Cedric");
        rota.assign(nextFriday, ced);
        rota.updateSchedule(apprentices, new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()));
        List<FridayLunch> result = rota.getSchedule();
        assertEquals(ced, result.get(0).getApprentice().get());
        assertEquals(new Date(Timestamp.valueOf("2016-06-17 07:00:00").getTime()), result.get(0).getDate());
        assertEquals(nick, result.get(1).getApprentice().get());
        assertEquals(new Date(Timestamp.valueOf("2016-06-24 07:00:00").getTime()), result.get(1).getDate());
        assertEquals(mollie, result.get(2).getApprentice().get());
        assertEquals(new Date(Timestamp.valueOf("2016-07-01 07:00:00").getTime()), result.get(2).getDate());
        assertEquals(nick, result.get(3).getApprentice().get());
        assertEquals(new Date(Timestamp.valueOf("2016-07-08 07:00:00").getTime()), result.get(3).getDate());
    }

}
