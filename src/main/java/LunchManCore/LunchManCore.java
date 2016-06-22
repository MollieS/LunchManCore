package LunchManCore;

import java.util.ArrayList;
import java.util.List;

public class LunchManCore {

    private Storage storage;
    private CustomDate date;

    public LunchManCore(Storage storage, CustomDate date) {
        this.storage = storage;
        this.date = date;
    }

    public static LunchManCore create(Storage storage, CustomDate date) {
        LunchManCore lunchManCore = new LunchManCore(storage, date);
        lunchManCore.updateAll();
        return lunchManCore;
    }

    private void updateAll() {
        Rota rota = loadUpdateSaveSchedule();
        if (rota.numFridaysUpdated() > 0) {
            loadUpdateSaveEmployees();
            loadUpdateSaveApprentices(rota.numFridaysUpdated());
            clearGuests();
        }
    }

    private void loadUpdateSaveApprentices(int numOfMoves) {
        List<Apprentice> apprentices = storage.getApprentices();
        for (int i = 0; i < numOfMoves; i++) {
            Apprentice movedApprentice = apprentices.remove(0);
            apprentices.add(movedApprentice);
        }
        storage.saveApprentices(apprentices);
    }

    public Rota loadUpdateSaveSchedule() {
        Rota rota = new Rota(4, date);
        rota.updateSchedule(storage.getSchedule(), storage.getApprentices());
        storage.saveSchedule(rota.getSchedule());
        return rota;
    }

    private void loadUpdateSaveEmployees() {
        List<Employee> employees = storage.getEmployees();
        for (Employee employee : employees) {
            employee.addOrder(null);
        }
        storage.saveEmployees(employees);
    }

    private void clearGuests() {
        storage.saveGuests(new ArrayList<>());
    }

    public List<Restaurant> getRestaurants() {
        return storage.getRestaurants();
    }

    public List<Employee> getEmployees() {
        return storage.getEmployees();
    }

    public List<Guest> getGuests() {
        return storage.getGuests();
    }

    public List<FridayLunch> getSchedule() {
        return storage.getSchedule();
    }

    public void assignApprenticeToLunch(Integer schedulePosition, String newName) {
        List<FridayLunch> schedule = getSchedule();
        FridayLunch fridayLunch = schedule.get(schedulePosition);
        fridayLunch.assignApprentice(new Apprentice(newName));
        storage.saveSchedule(schedule);
    }

    public void chooseNextFridayMenu(Integer restaurant) {
        List<Restaurant> restaurants = storage.getRestaurants();
        List<FridayLunch> schedule = getSchedule();
        FridayLunch fridayLunch = schedule.get(0);
        fridayLunch.assignRestaurant(restaurants.get(restaurant));
        storage.saveSchedule(schedule);
    }

    public void placeOrder(Integer employee, String order) {
        List<Employee> employees = storage.getEmployees();
        employees.get(employee).addOrder(order);
        storage.saveEmployees(employees);
    }

    public void addAGuest(String name, String order) {
        List<Guest> guests = storage.getGuests();
        Guest guest = new Guest(name, order);
        guests.add(guest);
        storage.saveGuests(guests);
    }
}
