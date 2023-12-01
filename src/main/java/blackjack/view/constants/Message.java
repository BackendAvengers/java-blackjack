package blackjack.view.constants;

public enum Message {
    INPUT_PLAYERS_NAME("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    INPUT_BET_AMOUNT_PER_PLAYER("%s의 베팅 금액은?"),
    INITIAL_CARD_DECK_HEADER("딜러와 %s에게 2장의 카드를 나누었습니다."),
    CARD_DECK_PER_PARTICIPANT("%s 카드: %s"),
    INPUT_RECEIVE_ADDITIONAL_CARD("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");


    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String DEALER_NAME = "딜러";
    private final String value;

    Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getValue(Object... args) {
        return String.format(this.value, args);
    }
}
