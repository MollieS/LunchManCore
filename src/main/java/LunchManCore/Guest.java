package LunchManCore;

public class Guest {

    private String name;
    private String order;

    public Guest(String name, String order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getOrder() {
        return order;
    }
}
