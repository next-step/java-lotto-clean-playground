package utils;

import domain.LottoNumber;
import domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusNumberParserTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되지 않으면 성공적으로 파싱된다.")
    void testParse_validBonusNumber() {
        LottoNumber bonusNumber = BonusNumberParser.parse("7", winningLotto);
        assertEquals(7, bonusNumber.value());
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 IllegalArgumentException이 발생한다.")
    void testParse_duplicateBonusNumber() {
        assertThatThrownBy(() -> BonusNumberParser.parse("1", winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("보너스 번호가 1부터 45 사이의 숫자가 아니면 IllegalArgumentException이 발생한다.")
    void testParse_outOfRangeBonusNumber() {
        assertThatThrownBy(() -> BonusNumberParser.parse("46", winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이여야 합니다.");

        assertThatThrownBy(() -> BonusNumberParser.parse("0", winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아닌 경우 IllegalArgumentException이 발생한다.")
    void testParse_nonNumericBonusNumber() {
        assertThatThrownBy(() -> BonusNumberParser.parse("abc", winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 숫자여야 합니다.");
    }
}
