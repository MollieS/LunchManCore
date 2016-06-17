package LunchManCore;

import java.time.LocalDate;

public class FridayDate implements Time {

    public LocalDate getDate() {
        return LocalDate.now();
    }
}
