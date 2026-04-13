package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningLottoTest {

    private Lotto winnerNumbers;

    @BeforeEach
    void setUp() {
        winnerNumbers = createLotto(List.of(1, 2, 3, 4, 5, 6));
    }

    private Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void validateDuplicateBonusTest() {
        // given
        LottoNumber duplicateBonus = new LottoNumber(3);

        // when // then
        assertThatThrownBy(() -> new WinningLotto(winnerNumbers, duplicateBonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3, 4, 5, 6, FIRST",   // 6개 일치
            "1, 2, 3, 4, 5, 7, SECOND",  // 5개 일치 + 보너스 일치
            "1, 2, 3, 4, 5, 8, THIRD",   // 5개 일치 (보너스 불일치)
            "1, 2, 3, 4, 10, 11, FOURTH", // 4개 일치
            "1, 2, 3, 10, 11, 12, FIFTH", // 3개 일치
            "1, 2, 10, 11, 12, 13, MISS"  // 2개 일치 (꽝)
    })
    @DisplayName("사용자 로또를 입력받아 정확한 등수를 판정한다.")
    void judgeRankTest(int n1, int n2, int n3, int n4, int n5, int n6, Rank expectedRank) {
        // given
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winnerNumbers, bonusNumber);
        Lotto userLotto = createLotto(List.of(n1, n2, n3, n4, n5, n6));

        // when
        Rank actualRank = winningLotto.judge(userLotto);

        // then
        assertThat(actualRank).isEqualTo(expectedRank);
    }
}
