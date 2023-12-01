package blackjack.exception;

public enum ErrorMessage {
    INVALID_CHARACTER_CONTAINS_ON_NUMERIC_INPUT("숫자로만 입력해주세요."),
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
