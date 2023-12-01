package blackjack.util;

import static blackjack.exception.ErrorMessage.INVALID_BET_AMOUNT_RANGE;
import static blackjack.exception.ErrorMessage.INVALID_CHARACTER_CONTAINS_ON_NUMERIC_INPUT;

import java.util.regex.Pattern;

public class Parser {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    private Parser() {
    }

    public static int parseInteger(String input) {
        String trimmedInput = input.trim();
        if (!NUMERIC_PATTERN.matcher(trimmedInput).matches()) {
            throw new IllegalArgumentException(INVALID_CHARACTER_CONTAINS_ON_NUMERIC_INPUT.getValue(trimmedInput));
        }

        try {
            return Integer.parseInt(trimmedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_RANGE.getValue(trimmedInput));
        }
    }
}
