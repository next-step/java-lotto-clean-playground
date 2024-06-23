package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberParserTest {

    @Nested
    @DisplayName("로또 번호 입력 테스트")
    class getLottoNumber {

        @Test
        @DisplayName("로또 번호 입력 테스트(보너스 볼 미포함)")
        void getLottoNumber_not_contain_bonusBall() {
            // given
            final String inputLastWeekNumber = "1,2,3,4,5,6";
            final LottoNumberParser lottoNumberParser = new LottoNumberParser(inputLastWeekNumber);
            final List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);

            // when
            final List<Integer> actual = lottoNumberParser.getRealLottoNumber();

            // then
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("로또 번호 입력 테스트 (보너스 볼 포함")
        void getLottoNumber_contain_bonusBall() {
            // given
            final String inputLastWeekNumber = "1,2,3,4,5,6";
            final int bonusBall = 7;
            final LottoNumberParser lottoNumberParser = new LottoNumberParser(inputLastWeekNumber);
            lottoNumberParser.addBonusBall(bonusBall);
            final List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);

            // when
            final List<Integer> actual = lottoNumberParser.getRealLottoNumber();

            // then
            assertEquals(expected, actual);
        }
    }

    @Test
    @DisplayName("문자열을 입력한 경우 테스트_예외처리")
    void getLottoNumber_generate_incorrect_exception() {
        // given
        final String lastWeekLottoNumber = "1, 2, 3, A, 4, 5";
        final var expected = "올바른 숫자 입력하세요.";

        // when
        final RuntimeException actual = assertThrows(RuntimeException.class, () -> new LottoNumberParser(lastWeekLottoNumber));

        // then
        assertEquals(expected, actual.getMessage());
    }
}
