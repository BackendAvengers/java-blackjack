package blackjack.dto;

import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;

public record CardDto(Symbol symbol, Type type) {
    public String toMessage() {
        return symbol.getShortValue() + type.getValue();
    }
}
