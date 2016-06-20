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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return menuLink != null ? menuLink.equals(that.menuLink) : that.menuLink == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (menuLink != null ? menuLink.hashCode() : 0);
        return result;
    }

    public String getMenuLink() {
        return menuLink;
    }
}
