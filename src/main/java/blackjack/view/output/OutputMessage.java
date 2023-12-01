package blackjack.view.output;

public enum OutputMessage {
    CARD_DEAL_MESSAGE("딜러와 %s에게 2장의 카드를 나누었습니다."),
    CARD_DISTRIBUTION("%s카드: %s");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
