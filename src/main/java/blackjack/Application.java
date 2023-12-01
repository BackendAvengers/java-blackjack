package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.view.input.BlackJackGameInputView;
import blackjack.view.input.InputValidator;
import blackjack.view.output.BlackJackGameOutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BlackJackController blackJackController = new BlackJackController(new BlackJackGameInputView(new InputValidator()),new BlackJackGameOutputView());
        blackJackController.run();
    }
}