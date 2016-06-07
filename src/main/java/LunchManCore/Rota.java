package LunchManCore;

import java.util.*;

public class Rota {

    private final int scheduleCapacity;
    private List<FridayLunch> schedule = new ArrayList<>();

    public Rota(int scheduleCapacity) {
        this.scheduleCapacity = scheduleCapacity;
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
            tmpList.add(new FridayLunch(new Date(387412987)));
        }
        return tmpList;
    }
}
