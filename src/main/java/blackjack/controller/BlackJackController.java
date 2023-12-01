package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.user.Player;
import blackjack.view.input.BlackJackGameInputView;

import java.util.List;

public class BlackJackController {

    private final BlackJackGameInputView inputView;

    public BlackJackController(BlackJackGameInputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        BlackJackGame blackJackGame = initializeGame();
    }

    private BlackJackGame initializeGame() {
        List<String> names = inputView.requestPlayerNames();
        List<Double> bettingMoneys = names.stream()
                .map(inputView::requestBettingMoney)
                .toList();

        return BlackJackGame.initializeGame(names, bettingMoneys);
    }
}
