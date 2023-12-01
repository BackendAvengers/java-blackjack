package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

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

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public List<Card> getCards() {
        return cards;
    }
}