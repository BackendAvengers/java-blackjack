package blackjack.domain.user;

import java.util.List;
import java.util.stream.IntStream;

public class PlayersFactory {

    private PlayersFactory() {
    }

    public static List<Player> createPlayers(List<String> names, List<Double> bettingMoneys) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), bettingMoneys.get(i)))
                .toList();
    }
}
