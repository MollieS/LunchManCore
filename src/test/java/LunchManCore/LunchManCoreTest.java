package LunchManCore;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LunchManCoreTest {

    private StorageFake storage;
    private LunchManCore lunchMan;

    @Before
    public void setUp() {
        this.storage = new StorageFake();
        this.lunchMan = LunchManCore.create(storage, new DateFake(2016, 6, 14));
    }

    @Test
    public void canLoadAListOfRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant("Nandos", "www.nandos.com"), new Restaurant("KFC", "www.KFC.com"));
        storage.saveRestaurants(restaurants);

        List<Restaurant> loadedRestaurants = lunchMan.getRestaurants();

        assertEquals("Nandos", loadedRestaurants.get(0).getName());
        assertEquals("www.nandos.com", loadedRestaurants.get(0).getMenuLink());
    }

    @Test
    public void canLoadAListOfEmployees() {
        List<Employee> loadedEmployees = lunchMan.getEmployees();

        assertEquals("Nick", loadedEmployees.get(0).getName());
    }

    @Test
    public void canLoadAListOfGuests() {
        List<Guest> guests = Arrays.asList(new Guest("Tom", "Eggs"));
        storage.setGuests(guests);

        List<Guest> loadedGuests = lunchMan.getGuests();

        assertEquals("Tom", loadedGuests.get(0).getName());
    }

    @Test
    public void canLoadTheCurrentSchedule() {
        Rota rota = lunchMan.loadUpdateSaveSchedule();

        assertEquals("Nick", rota.getSchedule().get(0).getApprentice().get().getName());
        assertEquals("Mollie", rota.getSchedule().get(1).getApprentice().get().getName());
    }

    @Test
    public void canAddAGuest() {
        lunchMan.addAGuest("Tom", "Egg");
        List<Guest> guests = lunchMan.getGuests();

        assertEquals("Tom", guests.get(0).getName());
    }

    @Test
    public void canAssignAnApprenticeToAFridayLunch() {
        lunchMan.assignApprenticeToLunch(1, "Rabea");
        Rota rota = lunchMan.loadUpdateSaveSchedule();

        assertEquals("Rabea", rota.getSchedule().get(1).getApprentice().get().getName());
    }

    @Test
    public void assigningApprenticeInToScheduleDisplacesOtherApprentices() {
        lunchMan.assignApprenticeToLunch(1, "Rabea");
        List<FridayLunch> schedule = lunchMan.getSchedule();

        assertEquals("Nick", schedule.get(0).getApprentice().get().getName());
        assertEquals("Rabea", schedule.get(1).getApprentice().get().getName());
        assertEquals("Mollie", schedule.get(2).getApprentice().get().getName());
        assertEquals("Nick", schedule.get(3).getApprentice().get().getName());
    }

    @Test
    public void canPlaceAnOrder() {
        lunchMan.placeOrder(1, "Pizza");
        List<Employee> loadedEmployees = lunchMan.getEmployees();

        assertEquals("Pizza", loadedEmployees.get(1).getOrder().get());
    }

    @Test
    public void canCopyAnOrder() {
        lunchMan.placeOrder(0, "Pizza");
        lunchMan.placeOrder(1, "Nick");

        List<Employee> loadedEmployees = lunchMan.getEmployees();

        assertEquals("Pizza", loadedEmployees.get(1).getOrder().get());
    }

    @Test
    public void canCopyAnOrderInAdvance() {
        lunchMan.placeOrder(1, "Nick");

        List<Employee> loadedEmployees = lunchMan.getEmployees();

        assertEquals("Nick", loadedEmployees.get(1).getOrder().get());

        lunchMan.placeOrder(0, "Pizza");

        List<Employee> loadedEmployees2 = lunchMan.getEmployees();

        assertEquals("Pizza", loadedEmployees2.get(1).getOrder().get());
    }

    @Test
    public void canDeleteAnOrder() {
        lunchMan.placeOrder(1, "Pizza");
        lunchMan.deleteOrder(1);
        List<Employee> loadedEmployees = lunchMan.getEmployees();

        assertEquals(Optional.empty(), loadedEmployees.get(1).getOrder());
    }

    @Test
    public void canChooseAMenuForTheNextFriday() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant("Nandos", "www.nandos.com"), new Restaurant("KFC", "www.KFC.com"));
        storage.saveRestaurants(restaurants);

        lunchMan.chooseNextFridayMenu(0);
        Rota rota = lunchMan.loadUpdateSaveSchedule();

        assertEquals("Nandos", rota.getSchedule().get(0).getRestaurant().get().getName());
    }

    @Test
    public void resetsEmployeeOrdersWhenFridayHasPassed() {
        StorageFake storage = new StorageFake();
        LunchManCore lunchMan = LunchManCore.create(storage, new DateFake(2016, 6, 20));

        lunchMan.placeOrder(0, "Pizza");
        LunchManCore newLunchMan = LunchManCore.create(storage, new DateFake(2016, 6, 26));

        List<Employee> employees1 = newLunchMan.getEmployees();
        assertEquals(Optional.empty(), employees1.get(0).getOrder());
    }

    @Test
    public void resetsGuestsWhenFridayHasPassed() {
        List<Guest> guests = Arrays.asList(new Guest("Nick", "Pizza"), new Guest("Mollie", "Egg"));
        StorageFake storage = new StorageFake();
        storage.setGuests(guests);
        LunchManCore.create(storage, new DateFake(2016, 6, 20));

        LunchManCore newLunchMan = LunchManCore.create(storage, new DateFake(2016, 6, 26));

        List<Guest> newGuests = newLunchMan.getGuests();
        assertTrue(newGuests.isEmpty());
    }

    @Test
    public void savesRotatedApprenticesAfterAssignment() {
        Storage storage = new StorageFake();
        LunchManCore.create(storage, new DateFake(2016, 6, 20));
        LunchManCore lunchMan = LunchManCore.create(storage, new DateFake(2016, 6, 26));

        List<Apprentice> apprentices = storage.getApprentices();

        assertEquals("Mollie", apprentices.get(0).getName());
    }
}
