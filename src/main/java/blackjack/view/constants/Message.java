package blackjack.view.constants;

public enum Message {
    INPUT_PLAYERS_NAME("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    INPUT_BET_AMOUNT_FORMAT("%s의 베팅 금액은?");

    private final String value;

    Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getValue(Object... args) {
        return String.format(INPUT_BET_AMOUNT_FORMAT.value, args);
    }
}
