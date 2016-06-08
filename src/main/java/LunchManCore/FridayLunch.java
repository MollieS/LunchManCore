package LunchManCore;

import java.time.LocalDate;
import java.util.Optional;

public class FridayLunch {

    private final LocalDate date;
    private Apprentice apprentice;

    public FridayLunch(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
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
