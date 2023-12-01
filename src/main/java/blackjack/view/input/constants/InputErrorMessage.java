package blackjack.view.input.constants;

public enum InputErrorMessage {

    PREFIX("[ERROR] "),

    NOT_BLANK_NAME_ERROR("이름은 공백이거나 비어있을 수 없습니다."),
    NOT_NUMBER_ERROR("금액은 숫자만 입력해주세요"),
    INVALID_DRAW_INPUT_ERROR("사용자 입력은 y혹은 n만 가능합니다."),
    DUPLICATE_NAME_ERROR("사용자의 이름은 중복될 수 없습니다.");

    private final String message;

    InputErrorMessage(String errorMessage) {
        this.message = errorMessage;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
