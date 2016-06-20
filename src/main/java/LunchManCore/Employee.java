package LunchManCore;

import java.util.Optional;

public class Employee {

    private final String name;
    private String order;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addOrder(String order) {
        this.order = order;
    }

    public Optional<String> getOrder() {
        if (this.order != null) {
            return Optional.of(this.order);
        }
        return Optional.empty();
    }

}
