package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class WinningLottoTest {

    @Test
    @DisplayName("정상적인 당첨 번호와 보너스 번호로 WinningLotto를 생성할 수 있다")
    void createWinningLottoWithValidNumbersAndBonus() {
        Lotto lotto = new Lotto(convert(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonus = new LottoNumber(7);

        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        assertThat(winningLotto.getNumbers()).containsExactlyElementsOf(lotto.getNumbers());
        assertThat(winningLotto.getBonus()).isEqualTo(bonus);
    }

    @Test
    @DisplayName("당첨 번호에 중복이 있으면 예외가 발생한다")
    void duplicateNumbersThrowsException() {
        List<LottoNumber> duplicated = convert(List.of(1, 2, 3, 4, 5, 5));
        LottoNumber bonus = new LottoNumber(7);

        assertThatThrownBy(() -> new WinningLotto(new Lotto(duplicated), bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다")
    void invalidSizeThrowsException() {
        List<LottoNumber> invalid = convert(List.of(1, 2, 3));
        LottoNumber bonus = new LottoNumber(7);

        assertThatThrownBy(() -> new WinningLotto(new Lotto(invalid), bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    void bonusNumberDuplicatedThrowsException() {
        Lotto lotto = new Lotto(convert(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonus = new LottoNumber(6); // 중복

        assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    private List<LottoNumber> convert(List<Integer> raw) {
        return raw.stream()
                .map(LottoNumber::new)
                .collect(toList());
    }
}
