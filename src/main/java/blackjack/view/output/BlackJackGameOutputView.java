package blackjack.view.output;

import blackjack.controller.dto.PlayerResultDto;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.List;
import java.util.stream.Collectors;

import static blackjack.view.output.OutputMessage.RESULT_BETTING_MONEY_MESSAGE_FORMAT;

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

    public void printDealerDrawCardMessage() {
        System.out.println(OutputMessage.DEALER_DRAW_CARD.getMessage());
    }

    public void printGamerResultMessage(Dealer dealer, int score) {
        String cardMessageFormat = getCardMessageFormat(dealer.getCards());
        System.out.println(String.format(OutputMessage.RESULT_GAME.getMessage(), DEALER_NAME, cardMessageFormat, score));
    }
    public void printGamerResultMessage(Player player, int score) {
        String name = player.getName();
        String cardMessageFormat = getCardMessageFormat(player.getCards());
        System.out.println(String.format(OutputMessage.RESULT_GAME.getMessage(), name, cardMessageFormat, score));
    }

    public void printGameResult(List<PlayerResultDto> playerResults, double dealerBenefitMoney) {
        System.out.println(OutputMessage.RESULT_BETTING_MONEY_MESSAGE.getMessage());
        printGameResult(DEALER_NAME, dealerBenefitMoney);

        playerResults.forEach(
                result -> printGameResult(result.getName(),result.getBettingMoney()));
    }

    private void printGameResult(String name, double benefitMoney) {
        System.out.println(
                String.format(RESULT_BETTING_MONEY_MESSAGE_FORMAT.getMessage(), name, benefitMoney));
    }
}
