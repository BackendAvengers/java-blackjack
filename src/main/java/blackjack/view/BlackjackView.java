package blackjack.view;

import static blackjack.view.constants.Message.CARD_DECK_PER_PARTICIPANT;
import static blackjack.view.constants.Message.DEALER_NAME;
import static blackjack.view.constants.Message.INITIAL_CARD_DECK_HEADER;
import static blackjack.view.constants.Message.INPUT_BET_AMOUNT_PER_PLAYER;
import static blackjack.view.constants.Message.INPUT_PLAYERS_NAME;
import static blackjack.view.constants.Message.INPUT_RECEIVE_ADDITIONAL_CARD;
import static blackjack.view.constants.Message.LINE_SEPARATOR;

import blackjack.dto.CardDeckDto;
import blackjack.dto.CardDto;
import blackjack.dto.InitialCardDeckDto;
import blackjack.io.ConsoleReader;
import blackjack.io.ConsoleWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackView {
    private final ConsoleReader reader;
    private final ConsoleWriter writer;

    public BlackjackView(ConsoleReader reader, ConsoleWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    private String inputWithMessage(String inputMessage) {
        writer.writeLine(inputMessage);
        return reader.readLine();
    }

    public String inputPlayersName(){
        return inputWithMessage(INPUT_PLAYERS_NAME.getValue());
    }

    public String inputBetAmount(String name){
        return inputWithMessage(INPUT_BET_AMOUNT_PER_PLAYER.getValue(name));
    }

    public String inputReceiveAdditionalCard(String name) {
        return inputWithMessage(INPUT_RECEIVE_ADDITIONAL_CARD.getValue(name));
    }

    private String joinWithNewLines(String... messages) {
        return String.join(LINE_SEPARATOR, messages);
    }

    public void outputInitialCardDeck(InitialCardDeckDto initialCardDeckDto) {
        Map<String, List<CardDto>> initialCardDeck = initialCardDeckDto.deck();
        String headerMessage = INITIAL_CARD_DECK_HEADER.getValue(getParticipantName(initialCardDeck));
        String cardDeckListMessage = getCardDeckListMessage(initialCardDeck);
        writer.writeLine(joinWithNewLines(LINE_SEPARATOR, headerMessage, cardDeckListMessage));
    }

    private String getParticipantName(Map<String, List<CardDto>> initialCardDeck) {
        return initialCardDeck.keySet().stream()
                .filter(name -> !name.equals(DEALER_NAME))
                .collect(Collectors.joining(", "));
    }

    private String getCardDeckListMessage(Map<String, List<CardDto>> initialCardDeck) {
        return initialCardDeck.entrySet().stream()
                .map(entry -> CARD_DECK_PER_PARTICIPANT.getValue(
                        entry.getKey(), getCardDeckMessage(entry.getValue())))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String getCardDeckMessage(List<CardDto> deck) {
        return deck.stream()
                .map(CardDto::toMessage)
                .collect(Collectors.joining(", "));
    }

    public void outputCardDeckPerPlayer(CardDeckDto cardDeckDto) {
        String message = CARD_DECK_PER_PARTICIPANT.getValue(cardDeckDto.name(), getCardDeckMessage(cardDeckDto.deck()));
        writer.writeLine(message);
    }
}
