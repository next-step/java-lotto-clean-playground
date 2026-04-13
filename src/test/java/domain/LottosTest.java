package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    private Lotto createMockLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    @Test
    @DisplayName("Lottos 객체는 생성 시 전달된 리스트의 크기를 올바르게 반환한다.")
    void sizeTest() {
        // given
        List<Lotto> lottoList = List.of(
                createMockLotto(List.of(1, 2, 3, 4, 5, 6)),
                createMockLotto(List.of(7, 8, 9, 10, 11, 12))
        );

        // when
        Lottos lottos = new Lottos(lottoList);

        // then
        assertThat(lottos.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("수동 로또 뭉치와 자동 로또 뭉치를 하나로 합칠 수 있다.")
    void mergeTest() {
        // given
        Lottos manual = new Lottos(List.of(createMockLotto(List.of(1, 2, 3, 4, 5, 6))));
        Lottos random = new Lottos(List.of(createMockLotto(List.of(10, 11, 12, 13, 14, 15))));

        // when
        Lottos merged = Lottos.merge(manual, random);

        // then
        assertThat(merged.size()).isEqualTo(2);
        assertThat(merged.getLottos()).hasSize(2);
    }

    @Test
    @DisplayName("여러 개의 로또 당첨 결과를 계산하여 계산기에 반영한다.")
    void calculateResultsTest() {
        // given
        Lottos lottos = new Lottos(List.of(
                createMockLotto(List.of(1, 2, 3, 10, 11, 12)),
                createMockLotto(List.of(1, 2, 3, 4, 11, 12))
        ));

        Lotto winnerNumbers = createMockLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winnerNumbers, bonusNumber);

        LottoCalculator calculator = new LottoCalculator();

        // when
        lottos.calculateResults(winningLotto, calculator);

        // then
        assertThat(calculator.getResult().get(Rank.FIFTH)).isEqualTo(1);
        assertThat(calculator.getResult().get(Rank.FOURTH)).isEqualTo(1);
        assertThat(calculator.getResult().get(Rank.FIRST)).isEqualTo(0);
    }

    @Test
    @DisplayName("getLottos()로 반환된 리스트를 외부에서 수정하려고 하면 예외가 발생한다.")
    void unmodifiableListTest() {
        // given
        Lottos lottos = new Lottos(List.of(createMockLotto(List.of(1, 2, 3, 4, 5, 6))));
        List<Lotto> lottoList = lottos.getLottos();

        // when // then
        assertThatThrownBy(() -> lottoList.add(createMockLotto(List.of(7, 8, 9, 10, 11, 12))))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
