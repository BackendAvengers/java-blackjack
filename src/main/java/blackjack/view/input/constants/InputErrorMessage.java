package blackjack.view.input.constants;

public enum InputErrorMessage {

    PREFIX("[ERROR] "),

    NOT_BLANK_NAME_ERROR("이름은 공백이거나 비어있을 수 없습니다."),
    NOT_NUMBER_ERROR("금액은 숫자만 입력해주세요");

    private final String message;

    InputErrorMessage(String errorMessage) {
        this.message = errorMessage;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
