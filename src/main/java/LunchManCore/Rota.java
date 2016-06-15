package LunchManCore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


public class Rota {

    private final int scheduleCapacity;
    private LocalDate startDate;
    private List<FridayLunch> schedule = new ArrayList<>();

    public Rota(int scheduleCapacity, LocalDate startDate) {
        this.scheduleCapacity = scheduleCapacity;
        this.startDate = startDate;
    }

    public List<FridayLunch> getSchedule() {
        return schedule;
    }

    public void assign(FridayLunch nextFriday, Apprentice apprentice) {
        nextFriday.assignApprentice(apprentice);
        if (schedule.size() < scheduleCapacity) {
            schedule.add(nextFriday);
        }
    }

    public void updateSchedule(List<Apprentice> apprentices) {
        for (FridayLunch friday : createFridays()) {
            Apprentice apprentice = apprentices.remove(0);
            assign(friday, apprentice);
            apprentices.add(apprentice);
        }
    }

    public LocalDate findNextFriday(LocalDate date) {
        return date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }

    public List<LocalDate> findNextFridays(LocalDate date) {
        List<LocalDate> dates = new ArrayList<>();
        for (int i = getSchedule().size(); i < scheduleCapacity; i++) {
            dates.add(findNextFriday(date));
            date = date.plus(1, ChronoUnit.WEEKS);
        }
        return dates;
    }

    public List<FridayLunch> createFridays() {
        List<FridayLunch> lunches = new ArrayList<>();

        if (!(schedule.isEmpty())) {
            startDate = schedule.get(schedule.size() - 1).getDate();
        }
        for (LocalDate date : findNextFridays(startDate)) {
            lunches.add(new FridayLunch(date));
        }
        return lunches;
    }

    public void setSchedule(List<FridayLunch> schedule) {
        this.schedule = schedule;
    }
}
