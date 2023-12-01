package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.dto.CardDeckDto;
import blackjack.dto.CardDeckResultDto;
import blackjack.dto.CardDeckResultListDto;
import blackjack.dto.CardDto;
import blackjack.dto.InitialCardDeckDto;
import blackjack.dto.ProfitDto;
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

    @Test
    void 플레이어의_카드덱을_출력한다() {
        //given
        CardDeckDto cardDeckDto = new CardDeckDto("a", List.of(
                new CardDto(Symbol.NINE, Type.CLOVER), new CardDto(Symbol.ACE, Type.HEART)));
        //when
        blackjackView.outputCardDeckPerPlayer(cardDeckDto);
        //then
        assertThat(stubConsoleWriter.getOutput()).isEqualTo(
                """                   
                        a 카드: 9클로버, A하트"""
        );
    }

    @Test
    void 게임참가자들의_카드덱과_카드합을_출력한다() {
        //given
        CardDeckResultListDto cardDeckResultListDto = new CardDeckResultListDto(List.of(
                new CardDeckResultDto("딜러", List.of(
                        new CardDto(Symbol.THREE, Type.DIAMOND),
                        new CardDto(Symbol.NINE, Type.CLOVER),
                        new CardDto(Symbol.EIGHT, Type.DIAMOND)), 20),
                new CardDeckResultDto("pobi", List.of(
                        new CardDto(Symbol.TWO, Type.HEART),
                        new CardDto(Symbol.EIGHT, Type.SPADE),
                        new CardDto(Symbol.ACE, Type.CLOVER)), 21)
        ));
        //when
        blackjackView.outputCardDeckResult(cardDeckResultListDto);
        //then
        assertThat(stubConsoleWriter.getOutput()).isEqualTo("""
                딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
                pobi 카드: 2하트, 8스페이드, A클로버 - 결과: 21""");
    }

    @Test
    void 모든게임참가자들의_최종수익을_출력한다() {
        //given
        Map<String, Double> profit = new LinkedHashMap<>();
        profit.put("딜러", 10000D);
        profit.put("pobi", 10000D);
        profit.put("jason", -20000D);
        ProfitDto profitDto = new ProfitDto(profit);
        //when
        blackjackView.outputProfit(profitDto);
        //then
        assertThat(stubConsoleWriter.getOutput()).isEqualTo("""
                ## 최종 수익
                딜러: 10000
                pobi: 10000
                jason: -20000""");
    }
}