package blackjack.util;

import static blackjack.exception.ErrorMessage.INVALID_BET_AMOUNT_RANGE;
import static blackjack.exception.ErrorMessage.INVALID_CHARACTER_CONTAINS_ON_NUMERIC_INPUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParserTest {

    @Nested
    class int타입_파싱 {

        @Test
        void 정상적인_입력값이라면_파싱에_성공한다() {
            //given
            String input = "10000";
            //when
            int totalRound = Parser.parseInteger(input);
            //then
            assertThat(totalRound).isEqualTo(Integer.valueOf(input));
        }

        @ValueSource(strings = {"-1", "12a", "1#2"})
        @ParameterizedTest
        void 유효하지않은_문자가_포함된_입력값이라면_예외를_발생시킨다(String input) {
            //given
            //when then
            assertThatThrownBy(() -> Parser.parseInteger(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(INVALID_CHARACTER_CONTAINS_ON_NUMERIC_INPUT.getValue());
        }

        @ValueSource(strings = {"2147483648"})
        @ParameterizedTest
        void int형_최댓값을_넘는다면_예외를_발생시킨다(String input) {
            //given
            //when then
            assertThatThrownBy(() -> Parser.parseInteger(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(INVALID_BET_AMOUNT_RANGE.getValue());
        }
    }
}