package domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningLottoTest {
    @Test
    void 보너스_번호와_당첨_번호가_중복되면_생성되지_않는다() {
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.from(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::from)
                        .toList()
        );
    }
}
