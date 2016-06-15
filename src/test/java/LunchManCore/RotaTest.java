package LunchManCore;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RotaTest {

    private Rota rota;
    private List<Apprentice> apprentices;

    @Before
    public void setUp() {
        this.rota = new Rota(4, LocalDate.of(2016, 6, 11));
        apprentices = createApprentices("Mollie", "Nick", "Cedric", "Priya", "Rabea");
    }

    @Test
    public void emptyScheduleAddsFourCorrectDates() {
        rota.emptySchedule();

        rota.updateSchedule(new ArrayList<>(), apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
        assertEquals(LocalDate.of(2016, 7, 1), rota.getSchedule().get(2).getDate());
        assertEquals(LocalDate.of(2016, 7, 8), rota.getSchedule().get(3).getDate());
    }

    @Test
    public void fullInDateScheduleDoesNotChangeDate() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-17", "2016-06-24", "2016-07-01", "2016-07-08");

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
        assertEquals(LocalDate.of(2016, 7, 1), rota.getSchedule().get(2).getDate());
        assertEquals(LocalDate.of(2016, 7, 8), rota.getSchedule().get(3).getDate());
    }

    @Test
    public void outOfDateScheduleHasDateUpdated() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-10", "2016-06-17", "2016-06-24", "2016-07-01");

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
        assertEquals(LocalDate.of(2016, 7, 1), rota.getSchedule().get(2).getDate());
        assertEquals(LocalDate.of(2016, 7, 8), rota.getSchedule().get(3).getDate());
    }

    @Test
    public void scheduleWithTwoOldDatesHasTwoDatesUpdates() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-03", "2016-06-10", "2016-06-17", "2016-06-24");

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
        assertEquals(LocalDate.of(2016, 7, 1), rota.getSchedule().get(2).getDate());
        assertEquals(LocalDate.of(2016, 7, 8), rota.getSchedule().get(3).getDate());
    }

    @Test
    public void scheduleWithFourOldDatesHasFourDatesUpdates() {
        List<FridayLunch> createdSchedule = createSchedule("2016-05-20", "2016-05-27", "2016-06-03", "2016-06-10");

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
        assertEquals(LocalDate.of(2016, 7, 1), rota.getSchedule().get(2).getDate());
        assertEquals(LocalDate.of(2016, 7, 8), rota.getSchedule().get(3).getDate());
    }


    @Test
    public void canAssignOneApprenticeToFourFridaysEmptySchedule() {
        List<Apprentice> apprentices = createApprentices("Mollie");
        rota.emptySchedule();

        rota.updateSchedule(new ArrayList<>(), apprentices);

        assertEquals("Mollie", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Mollie", rota.getSchedule().get(1).getApprentice().get().getName());
        assertEquals("Mollie", rota.getSchedule().get(2).getApprentice().get().getName());
        assertEquals("Mollie", rota.getSchedule().get(3).getApprentice().get().getName());
    }

    @Test
    public void canAssignTwoApprenticesToFourFridaysEmptySchedule() {
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick");
        rota.emptySchedule();

        rota.updateSchedule(new ArrayList<>(), apprentices);

        assertEquals("Mollie", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Nick", rota.getSchedule().get(1).getApprentice().get().getName());
        assertEquals("Mollie", rota.getSchedule().get(2).getApprentice().get().getName());
        assertEquals("Nick", rota.getSchedule().get(3).getApprentice().get().getName());
    }

    @Test
    public void canAssignFourApprenticesToFourFridaysEmptySchedule() {
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick", "Priya", "Rabea");
        rota.emptySchedule();

        rota.updateSchedule(new ArrayList<>(), apprentices);

        assertEquals("Mollie", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Nick", rota.getSchedule().get(1).getApprentice().get().getName());
        assertEquals("Priya", rota.getSchedule().get(2).getApprentice().get().getName());
        assertEquals("Rabea", rota.getSchedule().get(3).getApprentice().get().getName());
    }

    @Test
    public void inDateFullScheduleAssignsNobody() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-17", "2016-06-24", "2016-07-01", "2016-07-08");
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick", "Priya", "Rabea");
        assignApprentices(createdSchedule, apprentices);

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals("Mollie", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Nick", rota.getSchedule().get(1).getApprentice().get().getName());
        assertEquals("Priya", rota.getSchedule().get(2).getApprentice().get().getName());
        assertEquals("Rabea", rota.getSchedule().get(3).getApprentice().get().getName());
    }

    @Test
    public void outOfDateScheduleAssignsOneNewApprentice() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-10", "2016-06-17", "2016-06-24", "2016-07-01");
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick", "Priya", "Rabea");
        assignApprentices(createdSchedule, apprentices);

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals("Nick", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Priya", rota.getSchedule().get(1).getApprentice().get().getName());
        assertEquals("Rabea", rota.getSchedule().get(2).getApprentice().get().getName());
        assertEquals("Mollie", rota.getSchedule().get(3).getApprentice().get().getName());
    }

    @Test
    public void outOfDateScheduleAssignsTwoNewApprentices() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-03", "2016-06-10", "2016-06-17", "2016-06-24");
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick", "Priya", "Rabea");
        assignApprentices(createdSchedule, apprentices);

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals("Priya", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Rabea", rota.getSchedule().get(1).getApprentice().get().getName());
        assertEquals("Mollie", rota.getSchedule().get(2).getApprentice().get().getName());
        assertEquals("Nick", rota.getSchedule().get(3).getApprentice().get().getName());
    }

    private void assignApprentices(List<FridayLunch> schedule, List<Apprentice> apprentices) {
        for (int i = 0; i < schedule.size(); i++) {
            schedule.get(i).assignApprentice(apprentices.get(i));
        }
    }

    private List<Apprentice> createApprentices(String... names) {
        List<Apprentice> apprentices = new ArrayList<>();
        for (String name : names) {
            apprentices.add(new Apprentice(name));
        }
        return apprentices;
    }

    private List<FridayLunch> createSchedule(String... dates) {
        List<FridayLunch> schedule = new ArrayList<>();
        for (String date : dates) {
            schedule.add(new FridayLunch(LocalDate.parse(date)));
        }
        return schedule;
    }
}
