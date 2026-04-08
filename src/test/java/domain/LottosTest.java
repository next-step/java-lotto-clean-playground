package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @DisplayName("Lottos 객체가 당첨 번호를 받아 스스로 모든 로또를 대조하고 정확한 통계(LottoCalculator)를 반환한다.")
    @Test
    void matchAllTest() {
        // given
        Lotto winningLotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        Lotto myLotto1 = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), // 3개일치
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)
        ));
        Lotto myLotto2 = new Lotto(List.of(
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12), // 0개 일치
                new LottoNumber(13), new LottoNumber(14), new LottoNumber(15)
        ));

        Lottos lottos = new Lottos(List.of(myLotto1, myLotto2));

        // when (묻지 말고 시켜라!)
        LottoCalculator calculator = lottos.matchAll(winningLotto);

        // then
        assertThat(calculator.getResult().get(Rank.FIFTH)).isEqualTo(1); // 5등 1번
        assertThat(calculator.getResult().get(Rank.MISS)).isEqualTo(1);  // 꼴등 1번
    }
}