package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardBot {

    private final Queue<Card> cards;

    public CardBot(List<Card> cards) {
        List<Card> newCards = new LinkedList<>(cards);
        Collections.shuffle(newCards);
        this.cards = (Queue<Card>) newCards;
    }

    public Card getCard() {
        return cards.poll();
    }

    public List<Card> getDealCards() {
        return List.of(cards.poll(), cards.poll());
    }

}
