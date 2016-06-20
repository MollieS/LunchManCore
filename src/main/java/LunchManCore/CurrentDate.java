package LunchManCore;

import java.time.LocalDate;

public class CurrentDate implements CustomDate {

    public LocalDate getDate() {
        return LocalDate.now();
    }
}
