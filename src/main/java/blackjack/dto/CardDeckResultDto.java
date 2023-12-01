package blackjack.dto;

import java.util.List;

public record CardDeckResultDto(String name, List<CardDto> deck, int result) {
}
