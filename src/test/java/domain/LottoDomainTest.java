package domain;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class LottoDomainTest {

    @Nested
    @DisplayName("BonusBall 테스트")
    class BonusBallTest {

        @Test
        @DisplayName("보너스 볼이 기존 당첨 번호와 중복되면 예외 발생")
        void shouldThrowExceptionWhenBonusBallDuplicates() {
            Lotto winningLotto = new Lotto(List.of(
                    LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
            ));

            assertThrows(IllegalArgumentException.class, () -> new BonusBall(LottoNumber.of(6), winningLotto));
        }
    }

    @Nested
    @DisplayName("Lotto 테스트")
    class LottoTest {

        @Test
        @DisplayName("로또 번호는 6개여야 한다")
        void shouldThrowExceptionWhenLottoSizeIsInvalid() {
            assertThrows(IllegalArgumentException.class, () -> new Lotto(List.of(
                    LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3)
            )));
        }

        @Test
        @DisplayName("로또 번호는 중복될 수 없다")
        void shouldThrowExceptionWhenLottoHasDuplicateNumbers() {
            assertThrows(IllegalArgumentException.class, () -> new Lotto(List.of(
                    LottoNumber.of(1), LottoNumber.of(1), LottoNumber.of(2),
                    LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5)
            )));
        }

        @Test
        @DisplayName("당첨 로또와 일반 로또 비교 - 일치 개수 확인")
        void shouldCountMatchingNumbersCorrectly() {
            Lotto winningLotto = new Lotto(List.of(
                    LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
            ));
            Lotto playerLotto = new Lotto(List.of(
                    LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(7), LottoNumber.of(8), LottoNumber.of(9)
            ));

            int matchCount = Lotto.countMatchingNumbers(playerLotto, winningLotto);
            assertEquals(3, matchCount);
        }
    }

    @Nested
    @DisplayName("LottoMachine 테스트")
    class LottoMachineTest {

        @Test
        @DisplayName("랜덤 로또 생성 테스트")
        void shouldGenerateRandomLotto() {
            Lotto lotto = LottoMachine.getRandomLotto();
            assertEquals(6, lotto.getLottoNumbers().size());
        }
    }

    @Nested
    @DisplayName("LottoPurchaseAmount 테스트")
    class LottoPurchaseAmountTest {

        @Test
        @DisplayName("로또 금액이 1000원 단위가 아니면 예외 발생")
        void shouldThrowExceptionWhenAmountIsInvalid() {
            assertThrows(IllegalArgumentException.class, () -> new LottoPurchaseAmount(1500));
        }
    }

    @Nested
    @DisplayName("LottoStatistics 테스트")
    class LottoStatisticsTest {

        @Test
        @DisplayName("로또 수익률 계산 테스트")
        void shouldCalculateProfitRate() {
            Map<WinningRank, Integer> statistics = new HashMap<>();
            statistics.put(WinningRank.SIX_MATCH, 1);
            LottoStatistics lottoStatistics = new LottoStatistics(statistics);
            LottoPurchaseAmount purchaseAmount = new LottoPurchaseAmount(5000);

            double expectedProfitRate = (double) 2_000_000_000 / 5000;
            assertEquals(expectedProfitRate, lottoStatistics.calculateProfitRate(purchaseAmount), 0.01);
        }
    }

    @Nested
    @DisplayName("WinningLottoNumbers 테스트")
    class WinningLottoNumbersTest {

        @Test
        @DisplayName("당첨 번호와 보너스 볼 검증")
        void shouldVerifyWinningNumbersAndBonusBall() {
            Lotto winningLotto = new Lotto(List.of(
                    LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
            ));

            int bonusNumber = 7;
            WinningLottoNumbers winningNumbers = new WinningLottoNumbers(winningLotto, bonusNumber);

            Lotto playerLotto = new Lotto(List.of(
                    LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
            ));

            assertEquals(6, Lotto.countMatchingNumbers(playerLotto, winningNumbers.getWinningLotto()));
            assertFalse(winningNumbers.isBonusMatched(playerLotto));
        }
    }

    @Nested
    @DisplayName("WinningRank 테스트")
    class WinningRankTest {

        @Test
        @DisplayName("당첨 등급을 올바르게 반환해야 한다")
        void shouldReturnCorrectWinningRank() {
            assertEquals(WinningRank.SIX_MATCH, WinningRank.valueOf(6, false).orElse(null));
            assertEquals(WinningRank.FIVE_MATCH_WITH_BONUS, WinningRank.valueOf(5, true).orElse(null));
            assertEquals(WinningRank.FIVE_MATCH, WinningRank.valueOf(5, false).orElse(null));
        }
    }
}
