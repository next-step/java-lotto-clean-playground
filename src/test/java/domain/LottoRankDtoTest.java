package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import view.LottoRankDto;

import static org.junit.jupiter.api.Assertions.*;

class LottoRankDtoTest {

    @Nested
    @DisplayName("RankDto toString 메서드 테서트")
    class testToString {
        @Test
        @DisplayName("RankDto 일반 값 테스트")
        void testToString_standardNumber() {
            // given
            final LottoRankDto lottoRankDto = new LottoRankDto("3", 0);
            final String expected = "3개 일치 (5000)- 0개";

            // when
            final String actual = lottoRankDto.toString();

            // then
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("RankDto 보너스 값 테스트")
        void testToString_bonusNumber() {
            // given
            final LottoRankDto lottoRankDto = new LottoRankDto("5BONUS", 1);
            final String expected = "5개 일치, 보너스 볼 일치(30000000)- 1개";

            // when
            final String actual = lottoRankDto.toString();

            // then
            assertEquals(expected, actual);
        }
    }
}
