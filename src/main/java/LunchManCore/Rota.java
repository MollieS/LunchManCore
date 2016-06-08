package LunchManCore;

import java.util.*;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.FRIDAY;

public class Rota {

    private final int scheduleCapacity;
    private List<FridayLunch> schedule = new ArrayList<>();

    public Rota(int scheduleCapacity) {
        this.scheduleCapacity = scheduleCapacity;
    }

    public List<FridayLunch> getSchedule() {
        return schedule;
    }

    public void assign(FridayLunch nextFriday, Apprentice apprentice) {
        nextFriday.assignApprentice(apprentice);
        schedule.add(nextFriday);
    }

    public void updateSchedule(List<Apprentice> apprentices, Date dateymcdateface) {
        for (FridayLunch friday : createFridays(dateymcdateface)) {
            assign(friday, apprentices.get(0));
        }
    }

    public Date findNextFriday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int diff = Calendar.FRIDAY - calendar.get(DAY_OF_WEEK);
        if (!(diff > 0)) {
            diff += 7;
        }
        calendar.add(Calendar.DAY_OF_MONTH, diff);
        return calendar.getTime();
    }

    public List<Date> findNextFridays(Date date) {
        List<Date> dates = new ArrayList<>();
        for (int i = getSchedule().size(); i < scheduleCapacity; i++) {
            dates.add(findNextFriday(date));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            date = calendar.getTime();
        }
        return dates;
    }

    public List<FridayLunch> createFridays(Date startDate) {
        List<FridayLunch> lunches = new ArrayList<>();
        if (!(schedule.isEmpty())) {
            startDate = schedule.get(schedule.size() -1).getDate();
        }
        for (Date date : findNextFridays(startDate)) {
            lunches.add(new FridayLunch(date));
        }
        return lunches;
    }
//
//    private List<FridayLunch> createFridays(int scheduleSize) {
//        List<FridayLunch> tmpList = new ArrayList<>();
//        for (int i = scheduleSize; i < scheduleCapacity; i++) {
//            tmpList.add(new FridayLunch(findNextFriday()));
//            schedule.add(new FridayLunch(findNextFriday()))
//        }
//        return tmpList;
//    }
//
//    private Date findNextFriday() {
//        Date lastFriday;
//        if (schedule.size() > 0) {
//            lastFriday = schedule.get(schedule.size() - 1).getDate();
//        } else {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(startFromDate);
//            if (calendar.get(DAY_OF_WEEK) == Calendar.FRIDAY) {
//                lastFriday = calendar.getTime();
//            } else {
//                calendar.set(DAY_OF_WEEK, Calendar.FRIDAY);
//                calendar.add(Calendar.DAY_OF_MONTH, -7);
//                lastFriday = calendar.getTime();
//            }
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(lastFriday);
//        calendar.add(Calendar.DAY_OF_MONTH, 7);
//        return calendar.getTime();
//    }
}
