package LunchManCore;

import java.time.LocalDate;
import java.util.List;

public class LunchManCore {

    private Storage storage;

    public LunchManCore(Storage storage) {
        this.storage = storage;
    }

    public List<Restaurant> getRestaurants() {
        return storage.getRestaurants();
    }

    public List<Employee> getEmployees() {
        return storage.getEmployees();
    }

    public Rota getCurrentSchedule() {
        Rota rota = new Rota(4, LocalDate.now());
        rota.updateSchedule(storage.getSchedule(), storage.getApprentices());
        storage.saveSchedule(rota.getSchedule());
        return rota;
    }

    public void assignApprenticeToLunch(Integer schedulePosition, String newName) {
        Rota rota = getCurrentSchedule();
        FridayLunch fridayLunch = rota.getSchedule().get(schedulePosition);
        fridayLunch.assignApprentice(new Apprentice(newName));
        storage.saveSchedule(rota.getSchedule());
    }

    public void placeOrder(Integer employee, String order) {
        List<Employee> employees = storage.getEmployees();
        employees.get(employee).addOrder(order);
        storage.saveEmployees(employees);
    }

    public void chooseNextFridayMenu(Integer restaurant) {
        List<Restaurant> restaurants = storage.getRestaurants();
        Rota rota = getCurrentSchedule();
        FridayLunch fridayLunch = rota.getSchedule().get(0);
        fridayLunch.assignRestaurant(restaurants.get(restaurant));
        storage.saveSchedule(rota.getSchedule());
    }



}
