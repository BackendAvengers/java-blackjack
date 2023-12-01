package blackjack.controller;

import blackjack.controller.dto.PlayerResultDto;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.input.BlackJackGameInputView;
import blackjack.view.output.BlackJackGameOutputView;

import java.util.List;
import java.util.stream.IntStream;

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
        gameResult(blackJackGame);
    }

    private void gameResult(BlackJackGame blackJackGame) {
        if (blackJackGame.isDealerHit()) {
            outputView.printDealerDrawCardMessage();
            blackJackGame.addDealerCard();
        }
        printResultCardScoreMessage(blackJackGame);
        printResultBettingMoney(blackJackGame);
    }

    // 죄송합니다.... 보기 흉하네요

    private void printResultBettingMoney(BlackJackGame blackJackGame) {
        int dealerResultScore = blackJackGame.getDealerResultScore();
        List<Player> players = blackJackGame.getPlayers();

        if (blackJackGame.dealerExceedsBlackjackThreshold()) {
            List<Double> bettingMoneyResult = blackJackGame.getPlayersAllWinningResult();
            outputView.printGameResult(createResultDto(bettingMoneyResult, players),
                    calculateDealerBenefitMoney(bettingMoneyResult));
            return;
        }
        List<Double> playersBettingMoneyResult = blackJackGame.getPlayersBettingMoneyResult(dealerResultScore);
        outputView.printGameResult(createResultDto(playersBettingMoneyResult, players),
                calculateDealerBenefitMoney(playersBettingMoneyResult));
    }
    private double calculateDealerBenefitMoney(List<Double> resultBettingMoney) {
        double resultSum = resultBettingMoney.stream()
                .mapToDouble(value -> value)
                .sum();

        return resultSum * -1;
    }

    private List<PlayerResultDto> createResultDto(List<Double> bettingMoneyResults, List<Player> players) {
        return IntStream.range(0, players.size())
                .mapToObj(i -> new PlayerResultDto(players.get(i).getName(), bettingMoneyResults.get(i)))
                .toList();
    }

    private void printResultCardScoreMessage(BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        outputView.printGamerResultMessage(dealer, blackJackGame.getDealerResultScore());

        blackJackGame.getPlayers()
                .forEach(player ->
                    outputView.printGamerResultMessage(player, blackJackGame.getPlayerResultScore(player)));
    }

    private void startGame(BlackJackGame blackJackGame) {
        blackJackGame.dealInitialCards();
        outputView.printCardDistribution(blackJackGame.getDealer(),blackJackGame.getPlayers());

        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            if (blackJackGame.isBlackJack(player)) {
                continue;
            }
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
