package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.user.Player;
import blackjack.view.input.BlackJackGameInputView;
import blackjack.view.output.BlackJackGameOutputView;

import java.util.List;

public class BlackJackController {

    private final BlackJackGameInputView inputView;
    private final BlackJackGameOutputView outputView;

    public BlackJackController(BlackJackGameInputView inputView, BlackJackGameOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = initializeGame();
        startGame(blackJackGame);
    }

    private void startGame(BlackJackGame blackJackGame) {
        blackJackGame.dealInitialCards();
        outputView.printCardDistribution(blackJackGame.getDealer(),blackJackGame.getPlayers());

        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            while (inputView.shouldDrawCard(player)) {
                blackJackGame.addPlayerCard(player);
                outputView.printPlayerCardMessage(player);
            }
        }

    }

    private BlackJackGame initializeGame() {
        List<String> names = inputView.requestPlayerNames();
        List<Double> bettingMoneys = names.stream()
                .map(inputView::requestBettingMoney)
                .toList();

        return BlackJackGame.initializeGame(names, bettingMoneys);
    }
}
