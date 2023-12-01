package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayersFactory;

import java.util.List;

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
}
