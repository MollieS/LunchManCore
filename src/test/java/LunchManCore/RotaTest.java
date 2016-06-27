package LunchManCore;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RotaTest {

    private Rota rota;
    private List<Apprentice> apprentices;

    @Before
    public void setUp() {
        this.rota = new Rota(2, new DateFake(2016, 6, 11));
        apprentices = createApprentices("Mollie", "Nick", "Cedric", "Priya", "Rabea");
    }

    @Test
    public void emptyScheduleAddsFourCorrectDates() {
        rota.emptySchedule();

        rota.updateSchedule(new ArrayList<>(), apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
    }

    @Test
    public void fullInDateScheduleDoesNotChangeDate() {
        Rota rota = new Rota(2, new DateFake(2016, 6, 11));
        List<FridayLunch> createdSchedule = createSchedule("2016-06-17", "2016-06-24", "2016-07-01", "2016-07-08");

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
    }

    @Test
    public void outOfDateScheduleHasDateUpdated() {
        Rota rota = new Rota(2, new DateFake(2016, 6, 11));
        List<FridayLunch> createdSchedule = createSchedule("2016-06-10", "2016-06-17", "2016-06-24", "2016-07-01");

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
    }

    @Test
    public void scheduleWithTwoOldDatesHasTwoDatesUpdated() {
        Rota rota = new Rota(2, new DateFake(2016, 6, 11));
        List<FridayLunch> createdSchedule = createSchedule("2016-06-03", "2016-06-10", "2016-06-17", "2016-06-24");

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
    }

    @Test
    public void scheduleWithTwoOldDatesHasDatesRemoved() {
        Rota rota = new Rota(4, new DateFake(2016, 6, 11));
        List<FridayLunch> createdSchedule = createSchedule("2016-06-03", "2016-06-10", "2016-06-17", "2016-06-24");

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(0).getDate());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(1).getDate());
    }

    @Test
    public void canAssignOneApprenticeToFourFridaysEmptySchedule() {
        List<Apprentice> apprentices = createApprentices("Mollie");
        rota.emptySchedule();

        rota.updateSchedule(new ArrayList<>(), apprentices);

        assertEquals("Mollie", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Mollie", rota.getSchedule().get(1).getApprentice().get().getName());
    }

    @Test
    public void canAssignTwoApprenticesToFourFridaysEmptySchedule() {
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick");
        rota.emptySchedule();

        rota.updateSchedule(new ArrayList<>(), apprentices);

        assertEquals("Mollie", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Nick", rota.getSchedule().get(1).getApprentice().get().getName());
    }

    @Test
    public void canAssignFourApprenticesToFourFridaysEmptySchedule() {
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick", "Priya", "Rabea");
        rota.emptySchedule();

        rota.updateSchedule(new ArrayList<>(), apprentices);

        assertEquals("Mollie", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Nick", rota.getSchedule().get(1).getApprentice().get().getName());
    }

    @Test
    public void inDateFullScheduleAssignsNobody() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-17", "2016-06-24", "2016-07-01", "2016-07-08");
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick", "Priya", "Rabea");
        assignApprentices(createdSchedule, apprentices);

        rota.updateSchedule(createdSchedule, apprentices);

        assertEquals("Mollie", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Nick", rota.getSchedule().get(1).getApprentice().get().getName());
    }

    @Test
    public void outOfDateScheduleAssignsOneNewApprentice() {
        Rota rota = new Rota(4, new DateFake(2016, 6, 11));
        List<FridayLunch> createdSchedule = createSchedule("2016-06-10", "2016-06-17", "2016-06-24", "2016-07-01");
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick", "Priya", "Rabea");
        assignApprentices(createdSchedule, apprentices);

        rota.updateSchedule(createdSchedule, apprentices);

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
    }

    @Test
    public void ifScheduleIsUpdatedThenAssignedApprenticesDoNotChange() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-03", "2016-06-10", "2016-06-17", "2016-06-24");
        List<Apprentice> apprentices = createApprentices("Mollie", "Nick", "Priya", "Rabea");
        assignApprentices(createdSchedule, apprentices);

        DateFake date = new DateFake(2016, 6, 8);
        Rota rota = new Rota(2, date);

        rota.updateSchedule(createdSchedule, new ArrayList<>());

        assertEquals(3, rota.getSchedule().size());

        assertEquals("Nick", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals(LocalDate.of(2016, 6, 10), rota.getSchedule().get(0).getDate());
        assertEquals(Optional.empty(), rota.getSchedule().get(0).getRestaurant());

        assertEquals("Priya", rota.getSchedule().get(1).getApprentice().get().getName());
        assertEquals(LocalDate.of(2016, 6, 17), rota.getSchedule().get(1).getDate());
        assertEquals(Optional.empty(), rota.getSchedule().get(1).getRestaurant());

        assertEquals("Rabea", rota.getSchedule().get(2).getApprentice().get().getName());
        assertEquals(LocalDate.of(2016, 6, 24), rota.getSchedule().get(2).getDate());
        assertEquals(Optional.empty(), rota.getSchedule().get(2).getRestaurant());
    }

    @Test
    public void ifScheduleIsNotUpdatedThenAssignedRestaurantsDoesNotChange() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-03", "2016-06-10");
        createdSchedule.get(0).assignRestaurant(new Restaurant("Subway", "www.subway.com"));
        createdSchedule.get(1).assignRestaurant(new Restaurant("KFC", "www.kfc.com"));

        DateFake date = new DateFake(2016, 6, 2);
        Rota rota = new Rota(2, date);

        rota.updateSchedule(createdSchedule, new ArrayList<>());

        assertEquals(2, rota.getSchedule().size());

        assertEquals(LocalDate.of(2016, 6, 3), rota.getSchedule().get(0).getDate());
        //assertEquals(Optional.of(new Restaurant("Subway", "www.subway.com")), rota.getSchedule().get(0).getRestaurant());
        assertEquals("Subway", rota.getSchedule().get(0).getRestaurant().get().getName());
        assertEquals("www.subway.com", rota.getSchedule().get(0).getRestaurant().get().getMenuLink());

        assertEquals(LocalDate.of(2016, 6, 10), rota.getSchedule().get(1).getDate());
        assertEquals(Optional.of(new Restaurant("Subway", "www.subway.com")), rota.getSchedule().get(0).getRestaurant());
        assertEquals("KFC", rota.getSchedule().get(1).getRestaurant().get().getName());
        assertEquals("www.kfc.com", rota.getSchedule().get(1).getRestaurant().get().getMenuLink());
    }

    @Test
    public void knowsWhenFridaysHaveBeenRemoved() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-03", "2016-06-10");

        DateFake date = new DateFake(2016, 6, 8);
        Rota rota = new Rota(2, date);

        rota.updateSchedule(createdSchedule, createApprentices("Mollie", "Nick"));
        assertEquals(1, rota.numFridaysUpdated());
    }

    @Test
    public void knowsWhenFridayHasNotBeenRemoved() {
        List<FridayLunch> createdSchedule = createSchedule("2016-06-03", "2016-06-10", "2016-06-17", "2016-06-24");

        DateFake date = new DateFake(2016, 6, 2);
        Rota rota = new Rota(2, date);

        rota.updateSchedule(createdSchedule, new ArrayList<>());
        assertEquals(0, rota.numFridaysUpdated());

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
