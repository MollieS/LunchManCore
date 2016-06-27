package LunchManCore;

import java.util.List;

public interface Storage {

    List<FridayLunch> getSchedule();

    List<Apprentice> getApprentices() ;

    List<Employee> getEmployees();

    List<Restaurant> getRestaurants();

    List<Guest> getGuests();

    void saveEmployees(List<Employee> employees);

    void saveSchedule(List<FridayLunch> lunches);

    void saveGuests(List<Guest> guests);

    void saveRestaurants(List<Restaurant> restaurants);

    void saveApprentices(List<Apprentice> apprentices);
}
