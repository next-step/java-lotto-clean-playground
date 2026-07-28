package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.result.LottoResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {
    private static final List<Integer> VALID_LOTTO_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

    @Test
    @DisplayName("로또 번호 목록을 조회한다")
    void returnLottoNumbers() {
        LottoTicket lottoTicket = new LottoTicket(VALID_LOTTO_NUMBERS);

        List<Integer> result = lottoTicket.values();

        assertThat(result).containsExactlyElementsOf(VALID_LOTTO_NUMBERS);
    }

    @Test
    @DisplayName("당첨 로또와 비교해 일치 결과를 생성한다")
    void matchWinningLotto() {
        LottoTicket lottoTicket = new LottoTicket(List.of(1, 2, 3, 10, 11, 12));
        WinningLotto winningLotto = WinningLotto.of(
                VALID_LOTTO_NUMBERS,
                BonusBall.from(13)
        );

        LottoResult result = lottoTicket.match(winningLotto);

        assertThat(result.hasMatchCount(3)).isTrue();
        assertThat(result.hasUnmatchedBonusBall()).isTrue();
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void throwExceptionWhenNumberCountIsNotSix() {
        assertThatThrownBy(() -> new LottoTicket(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생한다")
    void throwExceptionWhenNumberIsDuplicated() {
        assertThatThrownBy(() -> new LottoTicket(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
}
