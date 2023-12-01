package blackjack.exception;

public enum ErrorMessage {
    INVALID_NUMERIC_INPUT("숫자로만 입력해주세요."),
    INVALID_CARD_DRAW_SIGNAL_INPUT("y나 n만 입력해주세요."),
    INVALID_PLAYER_NAME_INPUT("플레이어 이름은 알파벳 대소문자와 숫자만 가능합니다."),
    INVALID_BET_AMOUNT_RANGE("베팅 금액은 10,000원 이상 100,000,000원 이하여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return PREFIX + value;
    }

    public String getValue(Object arg) {
        return getValue() + ": " + arg;
    }
}
