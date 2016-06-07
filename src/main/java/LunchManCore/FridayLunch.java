package LunchManCore;

import java.util.Date;
import java.util.Optional;

public class FridayLunch {

    private final Date date;
    private Apprentice apprentice;

    public FridayLunch(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void assignApprentice(Apprentice apprentice) {
        this.apprentice = apprentice;
    }

    public Optional<Apprentice> getApprentice() {
        if (this.apprentice != null) {
            return Optional.of(this.apprentice);
        }
        return Optional.empty();
    }
}
