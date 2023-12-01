package blackjack.domain.card;

/**
 * 카드 한장을 의미하는 객체
 */
public class Card {

    private static final int ACE_BONUS_SCORE = 10;
    private final Symbol symbol;

    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    // TODO Card 관련 추가 기능 구현
    public int getScore() {
        if (isAceCard()) {
            return symbol.getScore() + ACE_BONUS_SCORE;
        }
        return symbol.getScore();
    }

    public boolean isAceCard() {
        return Symbol.ACE.equals(symbol);
    }

    public String getSymbol() {
        return symbol.getSymbol();
    }

    public String getType() {
        return type.getType();
    }
}