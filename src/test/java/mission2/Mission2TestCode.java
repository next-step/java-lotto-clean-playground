package mission2;

import domain.Lotto;
import domain.LottoCountByMatchNumber;
import domain.LottoSystem;
import domain.LottoWinningStatus;
import domain.Lottos;
import domain.WinningNumbers;
import mission1.FixedLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("로또 미션2 테스트")
public class Mission2TestCode {

    @Test
    @DisplayName("Lotto 번호가 6개가 아니면 예외가 발생한다")
    void 로또_번호가_6개가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("Lotto 번호가 1~45 범위를 벗어나면 예외가 발생한다")
    void 로또_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(List.of(1, 2, 3, 4, 5, 46)));
    }

    @Test
    @DisplayName("WinningNumbers는 6개가 아니면 예외가 발생한다")
    void 당첨번호가_6개가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new WinningNumbers(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("WinningNumbers는 중복된 번호가 있으면 예외가 발생한다")
    void 당첨번호에_중복이_있으면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new WinningNumbers(List.of(1, 1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("WinningNumbers는 Lotto와 일치하는 번호 개수를 정확히 센다")
    void 당첨번호와_일치하는_개수를_정확히_센다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 7, 8, 9));
        assertEquals(3, winningNumbers.countMatches(lotto));
    }

    @Test
    @DisplayName("LottoWinningStatus는 일치 개수별 로또 장수를 집계한다")
    void 일치_개수별_로또_장수를_집계한다() {
        Lotto lottoWithFourMatches = new Lotto(List.of(1, 2, 3, 4, 7, 8));
        Lotto lottoWithNoMatches = new Lotto(List.of(20, 21, 22, 23, 24, 25));

        Lottos lottos = new Lottos(List.of(lottoWithFourMatches, lottoWithNoMatches));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoWinningStatus lottoWinningStatus = new LottoWinningStatus(lottos, winningNumbers);

        LottoCountByMatchNumber result = lottoWinningStatus.getLottoCountByMatchNumber();

        assertEquals(1, result.get(4));
        assertEquals(0, result.get(3));
    }
}
