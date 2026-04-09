package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("전달된 로또 리스트의 크기가 기대 수량과 다르면 예외가 발생한다.")
    void validateSizeTest() {
        //given
        List<Lotto> lottoList = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)))
        );
        int expectedCount = 2;

        //when //then
        assertThatThrownBy(() -> new Lottos(lottoList, expectedCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구매 수량이 일치하지 않습니다.");
    }

    @Test
    @DisplayName("수동 로또와 자동 로또 묶음을 하나로 합친다.")
    void mergeTest() {
        //given
        Lotto lotto1 = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto2 = new Lotto(
                List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9), new LottoNumber(10),
                        new LottoNumber(11), new LottoNumber(12)));

        Lottos manual = new Lottos(List.of(lotto1), 1);
        Lottos random = new Lottos(List.of(lotto2), 1);

        //when
        Lottos merged = Lottos.merge(manual, random);

        //then
        assertThat(merged.size()).isEqualTo(2);
        assertThat(merged.getLottos()).containsExactly(lotto1, lotto2);
    }

    @Test
    @DisplayName("보유한 로또들의 당첨 결과를 계산하여 계산기에 반영한다.")
    void calculateResultsTest() {
        //given
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        Lottos lottos = new Lottos(List.of(lotto), 1);

        Lotto winnerNumbers = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(7), new LottoNumber(8), new LottoNumber(9)
        )); // 3개 일치
        LottoNumber bonusNumber = new LottoNumber(10);
        LottoCalculator calculator = new LottoCalculator();

        //when
        lottos.calculateResults(winnerNumbers, bonusNumber, calculator);

        //then
        // 3개 일치 -> 5등(Rank.FIFTH) 가정
        assertThat(calculator.getResult().get(Rank.FIFTH)).isEqualTo(1);
    }
}
