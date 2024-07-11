import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoNumber;
import domain.LottoStatus;

class LottoTest {

    private final LottoGenerator lottoGenerator = new LottoGenerator();
    private LottoStatus lottoStatus;

    @BeforeEach
    void setUp() {
        Lotto lotto = lottoGenerator.generateManualLotto("1, 2, 3, 4, 5, 6");
        lottoStatus = new LottoStatus(List.of(lotto));
    }

    @Test
    @DisplayName("로또 숫자 오류 테스트: 범위 초과")
    void checkRangeNumber() {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = new LottoNumber(50);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("잘못된 범위의 숫자 입니다. 번호: 50");
    }

    @Test
    @DisplayName("로또 숫자 오류 테스트: 중복된 숫자")
    void checkDuplicationNumber() {
        assertThatThrownBy(() -> {
            lottoGenerator.generateManualLotto("1, 1, 3, 4, 5, 6");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복되는 번호가 있습니다.");
    }

    @Test
    @DisplayName("로또 번호 개수 오류 테스트")
    void checkCountNumber() {
        assertThatThrownBy(() -> {
            lottoGenerator.generateManualLotto("1, 2, 3, 4, 5, 6, 7");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("번호의 개수가 올바르지 않습니다.");

    }

    @Test
    @DisplayName("보너스 숫자 오류 테스트: 중복된 숫자")
    void checkBonusNumber() {
        assertThatThrownBy(() -> {
            lottoStatus.setWinningNumber("1, 2, 3, 4, 5, 6");
            lottoStatus.setBonusNumber(1);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미 뽑힌 번호 입니다.");
    }
}
