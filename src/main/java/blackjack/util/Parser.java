package blackjack.util;

import static blackjack.exception.ErrorMessage.INVALID_BET_AMOUNT_RANGE;
import static blackjack.exception.ErrorMessage.INVALID_CHARACTER_CONTAINS_ON_NUMERIC_INPUT;
import static blackjack.exception.ErrorMessage.INVALID_CHARACTER_CONTAINS_ON_PLAYER_NAME_INPUT;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");
    private static final Pattern PLAYER_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9]+");
    private static final Pattern DELIMITER_PATTERN = Pattern.compile(",");

    private Parser() {
    }

    public static int parseInt(String input) {
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

    public static List<String> parsePlayerNameList(String input) {
        String trimmedInput = input.trim();
        List<String> playerNameList = Arrays.stream(DELIMITER_PATTERN.split(trimmedInput)).toList();
        if (!isValidPlayerName(playerNameList)) {
            throw new IllegalArgumentException(INVALID_CHARACTER_CONTAINS_ON_PLAYER_NAME_INPUT.getValue());
        }

        return playerNameList;
    }

    private static boolean isValidPlayerName(List<String> playerNameList) {
        return playerNameList.stream().allMatch(name -> PLAYER_NAME_PATTERN.matcher(name).matches());
    }
}
