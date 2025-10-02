import domain.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoService;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    @DisplayName("로또 숫자가 1~45 사이의 숫자가 아닐 경우 예외가 발생한다")
    void testLottoRange() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            List<LottoNumber> outOfRange = Arrays.asList(new LottoNumber(0), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
            new Lotto((SortedSet<LottoNumber>) outOfRange);
        });
        assertEquals("로또 번호는 1부터 45 사이의 숫자여야 합니다.", e.getMessage());
    }

    @Test
    @DisplayName("보너스볼 숫자가 기존 당첨번호와 중복될 경우 예외가 발생한다.")
    void bonusBallDuplicate() {
        LottoService lottoService = new LottoService();
        Lotto lottoAnswer = lottoService.parseLottoAnswer("1,2,3,4,5,6");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            lottoService.validateBonusBall(lottoAnswer, new LottoNumber(1));
        });
        assertEquals("보너스 볼은 당첨 번호와 중복될 수 없습니다.", e.getMessage());
    }

    @Test
    @DisplayName("로또 번호 수동 입력 시 중복된 숫자가 들어올 경우 예외가 발생한다.")
    void manualLottoDuplicate() {
        SortedSet<LottoNumber> duplicateNumbers = new TreeSet<>(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(5)));
        assertThatThrownBy(() -> new Lotto(duplicateNumbers)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("로또 숫자는 6개여야 하며, 중복될 수 없습니다.");

    }

}
