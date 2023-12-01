package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.Test;

class GameParticipantTest {

    @Test
    void 카드결과를_계산한다() {
        //given
        GameParticipant participant = new Dealer();
        participant.addCard(new Card(Symbol.ACE, Type.CLOVER));
        participant.addCard(new Card(Symbol.TWO, Type.CLOVER));
        participant.addCard(new Card(Symbol.ACE, Type.CLOVER));
        //when
        int result = participant.getResult();

        //then
        assertThat(result).isEqualTo(11 + 2 + 1);
    }
}