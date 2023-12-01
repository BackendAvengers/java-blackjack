package blackjack.exception;

public enum ErrorMessage {
    INVALID_NUMERIC_INPUT("숫자로만 입력해주세요."),
    INVALID_CARD_DRAW_SIGNAL_INPUT("y나 n만 입력해주세요."),
    INVALID_PLAYER_NAME_INPUT("플레이어 이름은 알파벳 대소문자와 숫자만 가능합니다."),
    INVALID_PLAYER_NAME_LENGTH("플레이어 이름은 2자 이상 10자 이하여야 합니다."),
    DUPLICATED_PLAYER_NAME("플레이어 이름은 고유해야 합니다."),
    INVALID_PLAYER_SIZE("게임에 참여할 수 있는 플레이어 수는 1명 이상 8명 이하입니다."),
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
