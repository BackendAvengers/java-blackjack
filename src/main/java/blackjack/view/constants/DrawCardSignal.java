package blackjack.view.constants;

import java.util.Optional;

public enum DrawCardSignal {
    DRAW("y"),
    NO_DRAW("n");

    private final String value;

    DrawCardSignal(String value) {
        this.value = value;
    }

    public static Optional<DrawCardSignal> findDrawCardSignal(String signal) {
        if (signal.equals(DRAW.value)) {
            return Optional.of(DRAW);
        }
        if (signal.equals(NO_DRAW.value)) {
            return Optional.of(NO_DRAW);
        }
        return Optional.empty();
    }

    public boolean canDraw() {
        return this.equals(DRAW);
    }
}
