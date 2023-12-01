package blackjack.domain.game;

public enum GameErrorMessage {
    PREFIX("[ERROR] "),
    INVALID_DRAW_CARD("카드를 한장 더 뽑을 수 없습니다.");

    private final String message;

    GameErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
