package blackjack.view.output;

public enum OutputMessage {
    CARD_DEAL_MESSAGE("딜러와 %s에게 2장의 카드를 나누었습니다."),
    CARD_DISTRIBUTION("%s카드: %s"),
    RESULT_GAME("%s카드: %s - 결과: %s"),
    DEALER_DRAW_CARD("딜러의 카드값이 16이하라 한장의 카드를 더 받았습니다."),
    RESULT_BETTING_MONEY_MESSAGE("## 최종수익"),
    RESULT_BETTING_MONEY_MESSAGE_FORMAT("%s: %.0f");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
