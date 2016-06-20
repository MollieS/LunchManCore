package LunchManCore;

import java.time.LocalDate;

public class DateFake implements CustomDate {

    private LocalDate date;

    public DateFake(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    public LocalDate getDate() {
        return date;
    }
}
