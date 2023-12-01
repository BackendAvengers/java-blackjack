package blackjack.dto;

import java.util.List;
import java.util.Map;

public record InitialCardDeckDto(Map<String, List<CardDto>> deck) {
}
