package blackjack.controller;

import blackjack.domain.card.CardFactory;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameParticipant;
import blackjack.domain.user.Player;
import blackjack.dto.CardDeckDto;
import blackjack.dto.CardDto;
import blackjack.dto.InitialCardDeckDto;
import blackjack.io.ConsoleReader;
import blackjack.io.ConsoleWriter;
import blackjack.util.Parser;
import blackjack.view.BlackjackView;
import blackjack.view.constants.DrawCardSignal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final ConsoleWriter consoleWriter = new ConsoleWriter();
    private final BlackjackView blackjackView = new BlackjackView(consoleReader, consoleWriter);

    public void run() {
        BlackjackGame blackjackGame = initGame(participantInGame());
        blackjackGame.distibuteCard();
        outputDistributionResult(blackjackGame);
        drawCardForAllPlayers(blackjackGame);

    }

    private void drawCardForAllPlayers(BlackjackGame blackjackGame) {
        List<Player> playerList = blackjackGame.getPlayerList();
        for (Player player : playerList) {
            DrawCardSignal drawCardSignal = getDrawCardSignal(player.getName());
            if (drawCardSignal.canDraw() && player.canDraw()) {
                blackjackGame.drawCard();
            }
            CardDeckDto cardDeckDto = new CardDeckDto(player.getName(), getCardDtoList(player));
            blackjackView.outputCardDeckPerPlayer(cardDeckDto);
        }
    }

    private BlackjackGame initGame(List<Player> players) {
        return new BlackjackGame(new Dealer(), players, CardFactory.create());
    }

    private List<Player> participantInGame() {
        List<Player> players = new ArrayList<>();

        List<String> playersName = getPlayersName();
        for (String name : playersName) {
            int bettingMoney = getBettingMoney(name);
            players.add(new Player(name, bettingMoney));
        }

        return players;
    }

    private List<String> getPlayersName() {
        while (true) {
            try {
                String playersNameInput = blackjackView.inputPlayersName();
                return Parser.parsePlayerNameList(playersNameInput);
            } catch (IllegalArgumentException e) {
                blackjackView.outputError(e.getMessage());
            }
        }
    }

    private int getBettingMoney(String name) {
        while (true) {
            try {
                String betAmountInput = blackjackView.inputBetAmount(name);
                return Parser.parseInt(betAmountInput);
            } catch (IllegalArgumentException e) {
                blackjackView.outputError(e.getMessage());
            }
        }
    }

    private void outputDistributionResult(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> playerList = blackjackGame.getPlayerList();
        Map<String, List<CardDto>> cardDeckPerParticipant = new LinkedHashMap<>();
        cardDeckPerParticipant.put("딜러", getCardDtoList(dealer));
        for (Player player : playerList) {
            cardDeckPerParticipant.put(player.getName(), getCardDtoList(player));
        }

        InitialCardDeckDto initialCardDeckDto = new InitialCardDeckDto(cardDeckPerParticipant);
        blackjackView.outputInitialCardDeck(initialCardDeckDto);
    }

    private List<CardDto> getCardDtoList(GameParticipant participant) {
        return participant.getCards().stream()
                .map(card -> new CardDto(card.getSymbol(), card.getType()))
                .toList();
    }

    private DrawCardSignal getDrawCardSignal(String name) {
        while (true) {
            try {
                String drawCardSignalInput = blackjackView.inputDrawCardSignal(name);
                return Parser.parseDrawCardSignal(drawCardSignalInput);
            } catch (IllegalArgumentException e) {
                blackjackView.outputError(e.getMessage());
            }
        }
    }
}
