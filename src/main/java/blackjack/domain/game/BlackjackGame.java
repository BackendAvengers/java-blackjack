package blackjack.domain.game;

import static blackjack.domain.constants.BlackjackConstraints.BLACKJACK_MAX_RESULT;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameParticipant;
import blackjack.domain.user.Player;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private static final int INITIAL_DISTRIBUTE_CARD_SIZE = 2;
    private final Dealer dealer;
    private final List<Player> playerList;
    private final List<GameParticipant> participants = new ArrayList<>();
    private final List<Card> cardList;

    public BlackjackGame(Dealer dealer, List<Player> playerList, List<Card> cardList) {
        this.dealer = dealer;
        this.playerList = playerList;
        this.participants.add(dealer);
        this.participants.addAll(playerList);

        this.cardList = cardList;
    }

    public void distributeCard() {
        for (GameParticipant participant : participants) {
            for (int i = 0; i < INITIAL_DISTRIBUTE_CARD_SIZE; i++) {
                participant.addCard(drawCard());
            }
        }
    }

    public Card drawCard() {
        int cardIdx = Randoms.pickNumberInRange(0, cardList.size() - 1);
        return cardList.get(cardIdx);
    }

    public Map<String, Double> calculateProfit() {
        Map<String, Double> profit = new LinkedHashMap<>();
        int dealerResult = dealer.getResult();
        boolean isDealerOver = isOver(dealerResult);

        profit.put("딜러", 0D);
        for (Player player : playerList) {
            int result = player.getResult();
            updateProfit(player, result, profit, isDealerOver, dealerResult);
        }

        return profit;
    }

    private void updateProfit(Player player, int result, Map<String, Double> profit, boolean isDealerOver,
                              int dealerResult) {
        if (isOver(result)) {
            profit.put(player.getName(), -player.getBettingMoney());
        } else if (isDealerOver) {
            profit.put(player.getName(), player.getBettingMoney());
        } else if (isBlackJack(player) && isBlackJack(dealer)) {
            profit.put(player.getName(), 0D);
        } else if (isBlackJack(player)) {
            profit.put(player.getName(), player.getBettingMoney() * 1.5D);
        } else if (result < dealerResult) {
            profit.put(player.getName(), -player.getBettingMoney());
        } else if (result > dealerResult) {
            profit.put(player.getName(), player.getBettingMoney());
        } else {
            profit.put(player.getName(), 0D);
        }
    }

    private boolean isBlackJack(GameParticipant participant) {
        return participant.getResult() == BLACKJACK_MAX_RESULT
                && participant.getCards().size() == INITIAL_DISTRIBUTE_CARD_SIZE;
    }

    private boolean isOver(int result) {
        return result > BLACKJACK_MAX_RESULT;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
