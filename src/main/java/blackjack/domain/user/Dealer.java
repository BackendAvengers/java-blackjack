package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public int getCardScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean hasAceCard() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }


    public List<Card> getCards() {
        return cards;
    }
}