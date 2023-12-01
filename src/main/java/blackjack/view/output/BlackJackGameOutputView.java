package blackjack.view.output;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGameOutputView {

    private static final String DEALER_NAME = "딜러";

    public void printCardDistribution(Dealer dealer, List<Player> players) {
        System.out.println(String.format(OutputMessage.CARD_DEAL_MESSAGE.getMessage(), getPlayerNames(players)));
        printDealerCard(getCardMessageFormat(dealer.getCards()));
        printPlayersCard(players);
    }

    private void printPlayersCard(List<Player> players) {
        players.forEach(this::printPlayerCardMessage);
    }

    private String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private String getCardMessageFormat(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getSymbol() + card.getType())
                .collect(Collectors.joining(", "));
    }

    private void printDealerCard(String cardMessageFormat) {
        System.out.println(String.format(
                OutputMessage.CARD_DISTRIBUTION.getMessage(), DEALER_NAME, cardMessageFormat));
    }

    public void printPlayerCardMessage(Player player) {
        String name = player.getName();
        String cardMessageFormat = getCardMessageFormat(player.getCards());
        System.out.println(String.format(OutputMessage.CARD_DISTRIBUTION.getMessage(), name, cardMessageFormat));
    }
}
