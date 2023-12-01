package blackjack.view.input;

import blackjack.view.input.constants.InputErrorMessage;

import java.util.List;

public class InputValidator {

    public void validateNames(List<String> names) {
        if (names.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException(InputErrorMessage.NOT_BLANK_NAME_ERROR.getMessage());
        }
    }

    public double validateBettingMoney(String money) {
        try {
            return Double.parseDouble(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputErrorMessage.NOT_NUMBER_ERROR.getMessage());
        }
    }
}
