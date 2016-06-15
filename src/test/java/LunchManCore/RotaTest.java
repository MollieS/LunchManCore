package LunchManCore;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RotaTest {

    private Rota rota;

    @Before
    public void setUp() {
        this.rota = new Rota(4, LocalDate.of(2016, 6, 11));
    }

    @Test
    public void canAssignOneApprenticeToAFridayLunch() {
        FridayLunch nextFriday = new FridayLunch(LocalDate.of(2016, 6, 10));
        Apprentice ced = new Apprentice("Cedric");
        rota.assign(nextFriday, ced);
        assertEquals(ced, rota.getSchedule().get(0).getApprentice().get());
    }


    @Test
    public void findsDateOfNextFridayWhenScheduleIsEmpty() {
        assertEquals(LocalDate.of(2016, 6, 17), rota.findNextFriday(LocalDate.of(2016, 6, 11)));
    }

    @Test
    public void findsTheNextFourFridaysWhenScheduleIsEmpty() {
        List<LocalDate> result = rota.findNextFridays(LocalDate.of(2016, 6, 11));

        assertEquals(LocalDate.of(2016, 6, 17), result.get(0));
        assertEquals(LocalDate.of(2016, 6, 24), result.get(1));
        assertEquals(LocalDate.of(2016, 7, 1), result.get(2));
        assertEquals(LocalDate.of(2016, 7, 8), result.get(3));
    }

    @Test
    public void createsTheNextFourFridayLunchesWhenScheduleIsEmpty() {
        List<FridayLunch> result = rota.createFridays();

        assertEquals(LocalDate.of(2016, 6, 17), result.get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), result.get(1).getDate());
        assertEquals(LocalDate.of(2016, 7, 1), result.get(2).getDate());
        assertEquals(LocalDate.of(2016, 7, 8), result.get(3).getDate());
    }

    @Test
    public void canSetTheSchedule() {
        FridayLunch fridayLunch1 = new FridayLunch(LocalDate.of(2016, 6, 17));
        FridayLunch fridayLunch2 = new FridayLunch(LocalDate.of(2016, 6, 24));
        FridayLunch fridayLunch3 = new FridayLunch(LocalDate.of(2016, 7, 1));
        FridayLunch fridayLunch4 = new FridayLunch(LocalDate.of(2016, 7, 8));
        List<FridayLunch> lunches = Arrays.asList(fridayLunch1, fridayLunch2, fridayLunch3, fridayLunch4);
        rota.setSchedule(lunches);
        assertEquals(lunches, rota.getSchedule());
    }

    @Test
    public void createsTheNext3FridayLunchesWhenScheduleHasOneFriday() {
        FridayLunch nextFriday = new FridayLunch(LocalDate.of(2016, 6, 17));
        Apprentice ced = new Apprentice("Cedric");
        rota.assign(nextFriday, ced);
        List<FridayLunch> result = rota.createFridays();
        assertEquals(LocalDate.of(2016, 6, 24), result.get(0).getDate());
        assertEquals(LocalDate.of(2016, 7, 1), result.get(1).getDate());
        assertEquals(LocalDate.of(2016, 7, 8), result.get(2).getDate());
        assertEquals(3, result.size());
    }

    @Test
    public void canAssignOneApprenticeToFourFridayLunches() {
        Apprentice priya = new Apprentice("Priya");
        List<Apprentice> apprentices = new LinkedList<>(Arrays.asList(priya));
        rota.updateSchedule(apprentices);
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
        rota.updateSchedule(apprentices);
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
        FridayLunch nextFriday = new FridayLunch(LocalDate.of(2016, 6, 17));
        Apprentice ced = new Apprentice("Cedric");
        rota.assign(nextFriday, ced);
        rota.updateSchedule(apprentices);
        List<FridayLunch> result = rota.getSchedule();
        assertEquals(ced, result.get(0).getApprentice().get());
        assertEquals(LocalDate.of(2016, 6, 17), result.get(0).getDate());
        assertEquals(nick, result.get(1).getApprentice().get());
        assertEquals(LocalDate.of(2016, 6, 24), result.get(1).getDate());
        assertEquals(mollie, result.get(2).getApprentice().get());
        assertEquals(LocalDate.of(2016, 7, 1), result.get(2).getDate());
        assertEquals(nick, result.get(3).getApprentice().get());
        assertEquals(LocalDate.of(2016, 7, 8), result.get(3).getDate());
    }

}
