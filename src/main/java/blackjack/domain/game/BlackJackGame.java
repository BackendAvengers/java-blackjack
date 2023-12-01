package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayersFactory;

import java.util.List;

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

    public boolean dealerPlayRound() {
        //딜러가 17 이하의 숫자를 가지면 한장 더가져야한다.
        if (dealer.getCardScore() < DEALER_THRESHOLD.getThreshold()) {
            dealer.addCard(cardBot.getCard());
        }

        //3장이 되었을 때 21이 넘으면 패배한다.
        return dealerExceedsBlackjackThreshold();
    }

    private boolean dealerExceedsBlackjackThreshold() {
        if (dealer.hasAceCard()) {
            return dealer.getCardScore() - 10 > BLACK_JACK_GAME_THRESHOLD.getThreshold();
        }
        return dealer.getCardScore() > BLACK_JACK_GAME_THRESHOLD.getThreshold();
    }

    public void addPlayerCard(Player player) {
        int cardScore = player.getCardScore();
        if (cardScore > BLACK_JACK_GAME_THRESHOLD.getThreshold()) {
            throw new IllegalStateException(GameErrorMessage.INVALID_DRAW_CARD.getMessage());
        }
        player.addCard(cardBot.getCard());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
