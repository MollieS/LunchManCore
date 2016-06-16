package LunchManCore;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LunchManCoreTest {

    private StorageFake storage;
    private LunchManCore lunchMan;

    @Before
    public void setUp() {
        this.storage = new StorageFake();
        this.lunchMan = new LunchManCore(storage);
    }

    @Test
    public void canLoadAListOfRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant("Nandos", "www.nandos.com"), new Restaurant("KFC", "www.KFC.com"));
        storage.setRestaurants(restaurants);

        List<Restaurant> loadedRestaurants = lunchMan.getRestaurants();

        assertEquals("Nandos", loadedRestaurants.get(0).getName());
        assertEquals("www.nandos.com", loadedRestaurants.get(0).getMenuLink());
    }

    @Test
    public void canLoadAListOfEmployees() {
        List<Employee> employees = Arrays.asList(new Employee("Nick"), new Employee("Mollie"));
        storage.setEmployees(employees);

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
        storage.setApprentices("Nick", "Mollie");
        storage.setSchedule(new ArrayList<>());

        Rota rota = lunchMan.getCurrentSchedule();

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
        storage.setApprentices("Nick", "Mollie");
        storage.setSchedule(new ArrayList<>());

        lunchMan.assignApprenticeToLunch(1, "Rabea");
        Rota rota = lunchMan.getCurrentSchedule();

        assertEquals("Rabea", rota.getSchedule().get(1).getApprentice().get().getName());
    }

    @Test
    public void canPlaceAnOrder() {
        List<Employee> employees = Arrays.asList(new Employee("Nick"), new Employee("Mollie"));
        storage.setEmployees(employees);

        lunchMan.placeOrder(1, "Pizza");
        List<Employee> loadedEmployees = lunchMan.getEmployees();

        assertEquals("Pizza", loadedEmployees.get(1).getOrder().get());
    }

    @Test
    public void canChooseAMenuForTheNextFriday() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant("Nandos", "www.nandos.com"), new Restaurant("KFC", "www.KFC.com"));
        storage.setRestaurants(restaurants);
        storage.setApprentices("Nick", "Mollie");
        storage.setSchedule(new ArrayList<>());

        lunchMan.chooseNextFridayMenu(0);
        Rota rota = lunchMan.getCurrentSchedule();

        assertEquals("Nandos", rota.getSchedule().get(0).getRestaurant().get().getName());
    }
}
