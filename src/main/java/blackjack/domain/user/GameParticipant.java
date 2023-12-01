package blackjack.domain.user;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class GameParticipant {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public abstract boolean canDraw();

    public int calculateResult() {
        return cards.stream()
                .map(Card::getScore)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
