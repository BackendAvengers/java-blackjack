package blackjack.domain.user;

import static blackjack.domain.constants.BlackjackConstraints.DEALER_DRAW_CARD_THRESHOLD;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends GameParticipant {

    @Override
    public boolean canDraw() {
        int result = getResult();
        return result <= DEALER_DRAW_CARD_THRESHOLD;
    }
}