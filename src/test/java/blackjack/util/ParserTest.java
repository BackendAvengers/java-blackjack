package blackjack.util;

import static blackjack.exception.ErrorMessage.INVALID_BET_AMOUNT_RANGE;
import static blackjack.exception.ErrorMessage.INVALID_CARD_DRAW_SIGNAL_INPUT;
import static blackjack.exception.ErrorMessage.INVALID_NUMERIC_INPUT;
import static blackjack.exception.ErrorMessage.INVALID_PLAYER_NAME_INPUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.view.constants.DrawCardSignal;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParserTest {

    @Nested
    class int타입_파싱 {

        @Test
        void 정상적인_입력값이라면_파싱에_성공한다() {
            //given
            String input = "10000";
            //when
            int totalRound = Parser.parseInt(input);
            //then
            assertThat(totalRound).isEqualTo(Integer.valueOf(input));
        }

        @ValueSource(strings = {"-1", "12a", "1#2"})
        @ParameterizedTest
        void 유효하지않은_문자가_포함된_입력값이라면_예외를_발생시킨다(String input) {
            //given
            //when then
            assertThatThrownBy(() -> Parser.parseInt(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(INVALID_NUMERIC_INPUT.getValue());
        }

        @ValueSource(strings = {"2147483648"})
        @ParameterizedTest
        void int형_최댓값을_넘는다면_예외를_발생시킨다(String input) {
            //given
            //when then
            assertThatThrownBy(() -> Parser.parseInt(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(INVALID_BET_AMOUNT_RANGE.getValue());
        }
    }

    @Nested
    class 플레이어_이름_입력값_파싱 {

        @Test
        void 정상적인_입력값이라면_파싱에_성공한다() {
            //given
            String input = "pobi,woni,jun";
            //when
            List<String> result = Parser.parsePlayerNameList(input);
            //then
            assertThat(result).hasSize(3)
                    .containsExactly("pobi", "woni", "jun");
        }

        @ValueSource(strings = {"이름1,이름2", "a*,a@@"})
        @ParameterizedTest
        void 유효하지않은_문자가_포함된_입력값이라면_예외를_발생시킨다(String input) {
            //given
            //when then
            assertThatThrownBy(() -> Parser.parsePlayerNameList(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(INVALID_PLAYER_NAME_INPUT.getValue());
        }
    }

    @Nested
    class 카드추가지급_여부_파싱 {

        static Stream<Arguments> getTestArgument() {
            return Stream.of(
                    Arguments.of("y", DrawCardSignal.DRAW),
                    Arguments.of("n", DrawCardSignal.NO_DRAW)
            );
        }

        @MethodSource("getTestArgument")
        @ParameterizedTest
        void 유효한_카드추가지급여부신호라면_파싱에_성공한다(String input, DrawCardSignal expected) {
            //given
            //when
            DrawCardSignal drawCardSignal = Parser.parseDrawCardSignal(input);
            //then
            assertThat(drawCardSignal).isEqualTo(expected);
        }

        @ValueSource(strings = {"1", " "})
        @ParameterizedTest
        void 유효하지않은_문자가_포함된_입력값이라면_예외를_발생시킨다(String input) {
            //given
            //when then
            assertThatThrownBy(() -> Parser.parseDrawCardSignal(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(INVALID_CARD_DRAW_SIGNAL_INPUT.getValue());
        }
    }
}