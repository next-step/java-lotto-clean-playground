package domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoNumberTest {

    @Test
    @DisplayName("calculate match count")
    void calculateMatchCount() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 10, 20, 30));
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList();

        Lotto purchasedLotto = new Lotto(lottoNumbers);

        int matchCount = winningLotto.countMatches(purchasedLotto);

        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("there is no match")
    void noMatch() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        List<LottoNumber> lottoNumbers = Stream.of(7, 8, 9, 10, 11, 12)
                .map(LottoNumber::new)
                .toList();

        Lotto purchasedLotto = new Lotto(lottoNumbers);
        int matchCount = winningLotto.countMatches(purchasedLotto);

        assertThat(matchCount).isEqualTo(0);
    }

}
