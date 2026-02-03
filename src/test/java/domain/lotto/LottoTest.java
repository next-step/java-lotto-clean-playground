package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    @DisplayName("로또는 6개의 번호를 가진다")
    @Test
    void createLotto() {
        // given
        LottoNumbers numbers = LottoNumbers.from("1, 2, 3, 4, 5, 6");

        // when
        Lotto lotto = new Lotto(numbers);

        // then
        assertThat(lotto.getLottoNumbers().getLottoNumbers()).hasSize(6);
    }

    @DisplayName("로또에 특정 번호가 포함되어 있는지 확인한다")
    @Test
    void containsNumber() {
        // given
        Lotto lotto = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber containedNumber = new LottoNumber(3);
        LottoNumber notContainedNumber = new LottoNumber(7);

        // when
        boolean containsThree = lotto.contains(containedNumber);
        boolean containsSeven = lotto.contains(notContainedNumber);

        // then
        assertThat(containsThree).isTrue();
        assertThat(containsSeven).isFalse();
    }

    @DisplayName("다른 로또와 일치하는 번호 개수를 반환한다")
    @Test
    void countMatchingNumbers() {
        // given
        Lotto lotto1 = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        Lotto lotto2 = new Lotto(LottoNumbers.from("1, 2, 3, 7, 8, 9"));

        // when
        int matchCount = lotto1.countMatchingNumbers(lotto2);

        // then
        assertThat(matchCount).isEqualTo(3);
    }
}
