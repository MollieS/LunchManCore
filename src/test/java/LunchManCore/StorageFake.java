package LunchManCore;

import java.util.ArrayList;
import java.util.List;

public class StorageFake implements Storage {

    private List<Restaurant> restaurants;
    private List<Employee> employees;
    private List<Apprentice> apprentices;
    private List<FridayLunch> schedule;

    public StorageFake() {
        this.apprentices = new ArrayList<>();
    }

    public List<FridayLunch> getSchedule() {
        return schedule;
    }

    public List<Apprentice> getApprentices() {
        return apprentices;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void saveSchedule(List<FridayLunch> lunches) {
        this.schedule = lunches;
    }

    public void saveEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setSchedule(List<FridayLunch> schedule) {
        this.schedule = schedule;
    }

    public void setApprentices(String...names) {
        for (String name : names) {
            apprentices.add(new Apprentice(name));
        }
    }
}
