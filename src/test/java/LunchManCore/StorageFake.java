package LunchManCore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StorageFake implements Storage {

    private List<Restaurant> restaurants;
    private List<Employee> employees;
    private List<Apprentice> apprentices;
    private List<FridayLunch> schedule;
    private List<Guest> guests;

    public StorageFake() {
        this.apprentices = createApprentices();
        this.restaurants = new ArrayList<>();
        this.employees = createEmployees();
        this.schedule = new ArrayList<>();
        this.guests = new ArrayList<>();
    }

    public List<FridayLunch> getSchedule() {
        return new ArrayList<>(schedule);
    }

    public List<Apprentice> getApprentices() {
        return new ArrayList<>(apprentices);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Guest> getGuests() {
        if (guests != null) {
            return guests;
        }
        return new ArrayList<>();
    }

    public void saveSchedule(List<FridayLunch> lunches) {
        this.schedule = new ArrayList<>(lunches);
    }

    public void saveGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public void saveEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void saveRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void saveApprentices(List<Apprentice> apprentices) {
        this.apprentices = apprentices;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    private List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();
        List<String> names = Arrays.asList("Nick", "Mollie");
        for (String name : names) {
            employees.add(new Employee(name));
        }
        return employees;
    }

    private List<Apprentice> createApprentices() {
        List<Apprentice> apprentices = new ArrayList<>();
        List<String> names = Arrays.asList("Nick", "Mollie");
        for (String name : names) {
            apprentices.add(new Apprentice(name));
        }
        return apprentices;
    }
}
