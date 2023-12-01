package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameParticipant;
import blackjack.domain.user.Player;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

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
        this.participants.add((GameParticipant) playerList);

        this.cardList = cardList;
    }

    public void distibuteCard() {
        for (GameParticipant participant : participants) {
            for (int i = 0; i < INITIAL_DISTRIBUTE_CARD_SIZE; i++) {
                participant.addCard(drawCard());
            }
        }
    }

    public Card drawCard() {
        int cardIdx = Randoms.pickNumberInRange(0, cardList.size() - 1);
        Card card = cardList.get(cardIdx);
        cardList.remove(cardIdx);
        return card;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
