package blackjack.dto;

import java.util.List;

public record CardDeckDto(String name, List<CardDto> deck) {
}
