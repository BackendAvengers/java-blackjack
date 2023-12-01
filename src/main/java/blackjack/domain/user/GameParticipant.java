package blackjack.domain.user;

import static blackjack.domain.card.Symbol.ACE;
import static blackjack.domain.card.Symbol.ACE_SECOND_SCORE;
import static blackjack.domain.constants.BlackjackConstraints.BLACKJACK_MAX_RESULT;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
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

    public int getResult() {
        int sumWithoutAce = getSumWithoutAce();

        int aceCount = getAceCount();
        if (aceCount > 0 && sumWithoutAce <= BLACKJACK_MAX_RESULT - ACE_SECOND_SCORE) {
            int result = sumWithoutAce + ACE_SECOND_SCORE;
            aceCount--;
            while (aceCount-- > 0) {
                result += ACE.getScore();
            }
            return result;
        }
        return sumWithoutAce + ACE.getScore();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getSymbol().equals(ACE))
                .count();
    }

    private int getSumWithoutAce() {
        return cards.stream()
                .filter(card -> !card.getSymbol().equals(Symbol.ACE))
                .map(Card::getScore)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
