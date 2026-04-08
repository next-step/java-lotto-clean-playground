package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @DisplayName("Lottos 객체가 WinningLotto를 받아 스스로 대조하고 정확한 통계를 반환한다.")
    @Test
    void matchAllTest() {
        // given (당첨 번호 1~6, 보너스 7)
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                )),
                new LottoNumber(7)
        );

        Lotto myLotto1 = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(7) // 2등 당첨
        ));
        Lotto myLotto2 = new Lotto(List.of(
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12),
                new LottoNumber(13), new LottoNumber(14), new LottoNumber(15) // 꽝
        ));

        Lottos lottos = new Lottos(List.of(myLotto1, myLotto2));

        // when
        LottoCalculator calculator = lottos.matchAll(winningLotto);

        // then
        assertThat(calculator.getResult().get(Rank.SECOND)).isEqualTo(1); // 2등 1번
        assertThat(calculator.getResult().get(Rank.MISS)).isEqualTo(1);  // 꽝 1번
    }
}