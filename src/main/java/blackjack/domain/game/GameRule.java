package blackjack.domain.game;

public enum GameRule {
    BLACK_JACK_GAME_THRESHOLD(21),
    DEALER_THRESHOLD(17);

    private final int threshold;

    GameRule(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }
}
