package blackjack.view.input;

import blackjack.view.input.constants.InputErrorMessage;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern CARD_DRAW_INPUT_PATTERN = Pattern.compile("[y|n]");

    public void validateNames(List<String> names) {
        blankNameCheck(names);
        duplicateNames(names);
    }

    private void blankNameCheck(List<String> names) {
        if (names.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException(InputErrorMessage.NOT_BLANK_NAME_ERROR.getMessage());
        }
    }

    private void duplicateNames(List<String> names) {
        int uniqueSize = new HashSet<>(names).size();
        if (uniqueSize != names.size()) {
            throw new IllegalArgumentException(InputErrorMessage.DUPLICATE_NAME_ERROR.getMessage());
        }
    }

    public double validateBettingMoney(String money) {
        try {
            return Double.parseDouble(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputErrorMessage.NOT_NUMBER_ERROR.getMessage());
        }
    }

    public void validateDrawAsk(String drawInput) {
        if (!CARD_DRAW_INPUT_PATTERN.matcher(drawInput).matches()) {
            throw new IllegalArgumentException(InputErrorMessage.INVALID_DRAW_INPUT_ERROR.getMessage());
        }
    }
}
