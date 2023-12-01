package blackjack.view;

import static blackjack.view.constants.Message.INPUT_BET_AMOUNT_FORMAT;
import static blackjack.view.constants.Message.INPUT_PLAYERS_NAME;
import static java.lang.String.format;

import blackjack.io.ConsoleReader;
import blackjack.io.ConsoleWriter;

public class BlackjackView {
    private final ConsoleReader reader = new ConsoleReader();
    private final ConsoleWriter writer = new ConsoleWriter();

    private String inputWithMessage(String inputMessage) {
        writer.writeLine(inputMessage);
        return reader.readLine();
    }

    public String inputPlayersName(){
        return inputWithMessage(INPUT_PLAYERS_NAME.getValue());
    }

    public String inputBetAmount(String name){
        return inputWithMessage(INPUT_BET_AMOUNT_FORMAT.getValue(name));
    }
}
