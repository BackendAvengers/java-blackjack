package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayersFactory;

import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.game.GameRule.BLACK_JACK_GAME_THRESHOLD;
import static blackjack.domain.game.GameRule.DEALER_THRESHOLD;

public class BlackJackGame {

    private final Dealer dealer;
    private final List<Player> players;
    private final CardBot cardBot;

    private BlackJackGame(Dealer dealer, List<Player> players, CardBot cardBot) {
        this.dealer = dealer;
        this.players = players;
        this.cardBot = cardBot;
    }

    public static BlackJackGame initializeGame(List<String> names, List<Double> bettingMoneys) {
        return new BlackJackGame(
                new Dealer(),
                PlayersFactory.createPlayers(names, bettingMoneys),
                new CardBot(CardFactory.create()));
    }

    public void dealInitialCards() {
        dealCardByDealer();
        dealCardByPlayers();
    }

    private void dealCardByDealer() {
        List<Card> dealCards = cardBot.getDealCards();
        dealCards.forEach(dealer::addCard);
    }

    private void dealCardByPlayers() {
        players.forEach(player -> {
            List<Card> dealCards = cardBot.getDealCards();
            dealCards.forEach(player::addCard);
        });
    }

    public boolean isDealerHit() {
        return dealer.getCardScore() < DEALER_THRESHOLD.getThreshold();
    }

    private boolean dealerExceedsBlackjackThreshold() {
        if (dealer.hasAceCard()) {
            return isOverBlackJackThreshold(dealer.getCardScore() - 10);
        }
        return isOverBlackJackThreshold(dealer.getCardScore());
    }

    public void addPlayerCard(Player player) {
        int cardScore = player.getCardScore();
        if (player.hasAceCard()) {
            cardScore -= 10;
        }
        validateDrawCard(cardScore);
        player.addCard(cardBot.getCard());
    }
    public void addDealerCard() {
        int cardScore = dealer.getCardScore();
        if (dealer.hasAceCard()) {
            cardScore -= 10;
        }
        validateDrawCard(cardScore);
        dealer.addCard(cardBot.getCard());
    }

    private void validateDrawCard(int cardScore) {
        if (isOverBlackJackThreshold(cardScore)) {
            throw new IllegalStateException(GameErrorMessage.INVALID_DRAW_CARD.getMessage());
        }
    }

    public boolean isOverBlackJackThreshold(int cardScore) {
        return cardScore > BLACK_JACK_GAME_THRESHOLD.getThreshold();
    }

    public int getPlayerResultScore(Player player) {
        return getResultScore(player.getCardScore(), player.hasAceCard());
    }

    public int getDealerResultScore() {
        return getResultScore(dealer.getCardScore(), dealer.hasAceCard());
    }

    private int getResultScore(int cardScore, boolean hasAceCard) {
        if (!hasAceCard) {
            return cardScore;
        }

        int notBonusScore = cardScore - 10;
        if (isOverBlackJackThreshold(cardScore)) {
            return notBonusScore;
        }
        return Math.max(cardScore, notBonusScore);
    }

    public List<Double> getPlayersBettingMoneyResult(int dealerCardScore) {
        return players.stream()
                .map(player -> getPlayersBettingMoneyResult(player, dealerCardScore))
                .toList();
    }
    public double getPlayersBettingMoneyResult(Player player, int dealerCardScore) {
        int playerResultScore = getPlayerResultScore(player);
        if (isOverBlackJackThreshold(playerResultScore)) {
            return player.getBettingMoney() * -1;
        }
        if (dealerCardScore < playerResultScore) {
            return playerResultScore * 2;
        }
        return playerResultScore;
    }

    public List<Double> getPlayersAllWinningResult() {
        return players.stream()
                .map(player -> player.getBettingMoney() * 2)
                .toList();
    }

    public double getPlayersBettingMoney() {
        return players.stream()
                .mapToDouble(Player::getBettingMoney)
                .sum();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
