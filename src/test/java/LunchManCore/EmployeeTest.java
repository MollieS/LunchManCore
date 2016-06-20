package LunchManCore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        this.employee = new Employee("Nick");
    }

    @Test
    public void hasAName() {
        assertEquals("Nick", employee.getName());
    }

    @Test
    public void hasAnOrder() {
        employee.addOrder("Peri Peri Chicken");
        assertEquals("Peri Peri Chicken", employee.getOrder().get());
    }

}
