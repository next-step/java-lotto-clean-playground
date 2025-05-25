import domain.Lotto;
import domain.LottoNumber;
import domain.Money;
import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DomainTest {

    // LottoNumber 테스트
    @DisplayName("로또 번호가 1부터 45 사이의 숫자일 때 정상적으로 생성된다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 44, 45})
    void createLottoNumber_ValidRange_Success(int number) {
        assertThatCode(() -> new LottoNumber(number))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호가 1보다 작거나 45보다 클 때 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100})
    void createLottoNumber_InvalidRange_ThrowsException(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @DisplayName("같은 번호의 LottoNumber는 동일하다")
    @Test
    void lottoNumber_Equals_Success() {
        LottoNumber number1 = new LottoNumber(10);
        LottoNumber number2 = new LottoNumber(10);

        assertThat(number1).isEqualTo(number2);
    }

    // Lotto 테스트
    @DisplayName("로또 번호 6개가 정상적으로 생성된다")
    @Test
    void createLotto_ValidNumbers_Success() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        assertThatCode(() -> new Lotto(numbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호가 6개가 아닐 때 예외가 발생한다")
    @Test
    void createLotto_InvalidCount_ThrowsException() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호에 중복이 있을 때 예외가 발생한다")
    @Test
    void createLotto_DuplicateNumbers_ThrowsException() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(1), new LottoNumber(5), new LottoNumber(6)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호와 일치하는 개수를 정확히 계산한다")
    @Test
    void lotto_CountMatchingNumbers_Success() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );
        List<LottoNumber> winningNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(7), new LottoNumber(8), new LottoNumber(9)
        );

        Lotto lotto = new Lotto(lottoNumbers);
        int matchCount = lotto.countMatchingNumbers(winningNumbers);

        assertThat(matchCount).isEqualTo(3);
    }

    // Money 테스트
    @DisplayName("1000원 단위의 양수 금액으로 Money 객체가 생성된다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 5000, 10000})
    void createMoney_ValidAmount_Success(int amount) {
        assertThatCode(() -> new Money(amount))
                .doesNotThrowAnyException();
    }

    @DisplayName("0 이하의 금액일 때 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -500})
    void createMoney_NonPositiveAmount_ThrowsException(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구매 금액은 0원보다 커야 합니다.");
    }

    @DisplayName("1000원 단위가 아닌 금액일 때 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {500, 1500, 2200, 3333})
    void createMoney_NotThousandUnit_ThrowsException(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구매 금액은 1000원 단위여야 합니다.");
    }

    @DisplayName("로또 구매 가능 개수를 정확히 계산한다")
    @Test
    void money_CalculateLottoCount_Success() {
        Money money = new Money(5000);

        assertThat(money.calculateLottoCount()).isEqualTo(5);
    }

    @DisplayName("수익률을 정확히 계산한다")
    @Test
    void money_CalculateEarningRate_Success() {
        Money money = new Money(10000);
        long totalPrize = 15000;

        double earningRate = money.calculateEarningRate(totalPrize);

        assertThat(earningRate).isEqualTo(1.5);
    }

    // Rank 테스트
    @DisplayName("6개 일치 시 1등이다")
    @Test
    void rank_SixMatches_FirstRank() {
        Rank rank = Rank.valueOf(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 일치 + 보너스 볼 일치 시 2등이다")
    @Test
    void rank_FiveMatchesWithBonus_SecondRank() {
        Rank rank = Rank.valueOf(5, true);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 일치 시 3등이다")
    @Test
    void rank_FiveMatches_ThirdRank() {
        Rank rank = Rank.valueOf(5, false);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("2개 이하 일치 시 미당첨이다")
    @Test
    void rank_TwoOrLessMatches_NoRank() {
        assertThat(Rank.valueOf(2, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.valueOf(1, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.valueOf(0, false)).isEqualTo(Rank.NONE);
    }

    @DisplayName("당첨 등수는 당첨 여부를 올바르게 반환한다")
    @Test
    void rank_IsWinning_Success() {
        assertThat(Rank.FIRST.isWinning()).isTrue();
        assertThat(Rank.SECOND.isWinning()).isTrue();
        assertThat(Rank.THIRD.isWinning()).isTrue();
        assertThat(Rank.FOURTH.isWinning()).isTrue();
        assertThat(Rank.FIFTH.isWinning()).isTrue();
        assertThat(Rank.NONE.isWinning()).isFalse();
    }
}
