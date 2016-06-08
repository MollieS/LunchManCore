package LunchManCore;

import java.util.*;

import static java.util.Calendar.DAY_OF_WEEK;

public class Rota {

    private final int scheduleCapacity;
    private final Date startFromDate;
    private List<FridayLunch> schedule = new ArrayList<>();

    public Rota(int scheduleCapacity, Date startFromDate) {
        this.scheduleCapacity = scheduleCapacity;
        this.startFromDate = startFromDate;
    }

    public List<FridayLunch> getSchedule() {
        return schedule;
    }

    public void assign(FridayLunch nextFriday, Apprentice ced) {
        nextFriday.assignApprentice(ced);
        schedule.add(nextFriday);
    }

    public void updateSchedule(List<Apprentice> apprentices) {
        for (FridayLunch friday : createFridays()) {
            assign(friday, apprentices.get(0));
        }
    }

    private List<FridayLunch> createFridays() {
        List<FridayLunch> tmpList = new ArrayList<>();
        for (int i = getSchedule().size(); i < scheduleCapacity; i++) {
            tmpList.add(new FridayLunch(findNextFriday()));
        }
        return tmpList;
    }

    private Date findNextFriday() {
        Date lastFriday;
        if (schedule.size() > 0) {
            lastFriday = schedule.get(schedule.size() - 1).getDate();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startFromDate);
            if (calendar.get(DAY_OF_WEEK) == Calendar.FRIDAY) {
                lastFriday = calendar.getTime();
            } else {
                calendar.set(DAY_OF_WEEK, Calendar.FRIDAY);
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                lastFriday = calendar.getTime();
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastFriday);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        return calendar.getTime();
    }
}
