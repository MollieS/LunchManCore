package LunchManCore;

public class Restaurant {

    private String name;
    private String menuLink;

    public Restaurant(String name, String menuLink) {
        this.name = name;
        this.menuLink = menuLink;
    }

    public String getName() {
        return name;
    }

    public String getMenuLink() {
        return menuLink;
    }
}
