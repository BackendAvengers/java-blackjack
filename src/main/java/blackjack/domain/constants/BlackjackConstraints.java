package blackjack.domain.constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlackjackConstraints {
    public static final int BLACKJACK_MAX_RESULT = 21;
    public static final int DEALER_DRAW_CARD_THRESHOLD = 16;
    public static final int PLAYER_NAME_MIN_LENGTH = 2;
    public static final int PLAYER_NAME_MAX_LENGTH = 10;
    public static final int PLAYER_MIN_SIZE = 1;
    public static final int PLAYER_MAX_SIZE = 8;
    public static final int BETTING_MONEY_MIN = 10_000;
    public static final int BETTING_MONEY_MAX = 100_000_000;


    private BlackjackConstraints() {
    }

    public static boolean isValidPlayerNameLength(String name) {
        int length = name.length();
        return PLAYER_NAME_MIN_LENGTH <= length && length <= PLAYER_NAME_MAX_LENGTH;
    }

    public static boolean isUniquePlayerName(List<String> playerNames) {
        Set<String> uniquePlayers = new HashSet<>(playerNames);
        return uniquePlayers.size() == playerNames.size();
    }

    public static boolean isValidPlayerSize(List<String> player) {
        int size = player.size();
        return PLAYER_MIN_SIZE <= size && size <= PLAYER_MAX_SIZE;
    }

    public static boolean isValidBettingMoneyRange(int bettingMoney) {
        return BETTING_MONEY_MIN <= bettingMoney && bettingMoney <= BETTING_MONEY_MAX;
    }
}
