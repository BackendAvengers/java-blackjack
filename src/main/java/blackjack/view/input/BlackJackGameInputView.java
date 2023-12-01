package blackjack.view.input;

import blackjack.view.input.constants.InputMessage;

import java.util.List;

public class BlackJackGameInputView extends ConsoleInput {

    private final InputValidator inputValidator;

    public BlackJackGameInputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public List<String> requestPlayerNames() {
        System.out.println(InputMessage.REQUEST_NAME.getMessage());
        List<String> names = getNames();
        inputValidator.validateNames(names);
        return names;
    }

    private List<String> getNames() {
        return List.of(readLine().split(","));
    }

    public double requestBettingMoney(String name) {
        System.out.println(String.format(InputMessage.REQUEST_BETTING_MONEY_FORMAT.getMessage(),name));
        return getBettingMoney();
    }

    private double getBettingMoney() {
        return inputValidator.validateBettingMoney(readLine());
    }
}
