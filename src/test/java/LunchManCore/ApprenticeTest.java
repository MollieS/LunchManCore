package LunchManCore;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ApprenticeTest {

    @Test
    public void hasAName() {
        Apprentice mollie = new Apprentice("Mollie");
        assertEquals("Mollie", mollie.getName());
    }

}
