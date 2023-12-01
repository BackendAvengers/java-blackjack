package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.dto.CardDto;
import blackjack.dto.InitialCardDeckDto;
import blackjack.io.ConsoleReader;
import blackjack.stub.StubConsoleWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlackjackViewTest {
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final StubConsoleWriter stubConsoleWriter = new StubConsoleWriter();
    private final BlackjackView blackjackView = new BlackjackView(consoleReader, stubConsoleWriter);

    @Test
    void 초기카드분배후_카드덱결과를_출력한다() {
        //given
        Map<String, List<CardDto>> deck = new LinkedHashMap<>();
        deck.put("딜러", List.of(
                new CardDto(Symbol.SIX, Type.CLOVER), new CardDto(Symbol.TEN, Type.HEART)));
        deck.put("a", List.of(
                new CardDto(Symbol.NINE, Type.CLOVER), new CardDto(Symbol.ACE, Type.HEART)));
        deck.put("b", List.of(
                new CardDto(Symbol.KING, Type.SPADE), new CardDto(Symbol.QUEEN, Type.DIAMOND)));
        InitialCardDeckDto initialCardDeckDto = new InitialCardDeckDto(deck);
        //when
        blackjackView.outputInitialCardDeck(initialCardDeckDto);
        //then
        assertThat(stubConsoleWriter.getOutput()).isEqualTo("""
                                        
                                                
                딜러와 a, b에게 2장의 카드를 나누었습니다.
                딜러 카드: 6클로버, 10하트
                a 카드: 9클로버, A하트
                b 카드: K스페이드, Q다이아몬드"""
        );
    }
}