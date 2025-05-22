package service;

import domain.Lotto;
import domain.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    private LottoNumberGenerator numberGenerator;
    private LottoGenerator lottoGenerator;

    @BeforeEach
    void setUp() {
        numberGenerator = new LottoNumberGenerator();
        lottoGenerator = new LottoGenerator(numberGenerator);
    }

    @Test
    @DisplayName("수동 로또와 자동 로또가 정확하게 생성되어야 한다")
    void generate_withManualAndAutoLottos_shouldReturnCorrectLottos() {
        List<Lotto> manualLottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),
                new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9), new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)))
        );
        int autoCount = 3;

        List<Lotto> result = lottoGenerator.generate(manualLottos, autoCount);

        assertThat(result.size()).isEqualTo(manualLottos.size() + autoCount);
    }

    @Test
    @DisplayName("자동 로또만 생성되어야 한다 (수동 로또 없이)")
    void generate_withAutoLottosOnly_shouldReturnCorrectAutoLottos() {
        List<Lotto> manualLottos = List.of();
        int autoCount = 3;

        List<Lotto> result = lottoGenerator.generate(manualLottos, autoCount);

        assertThat(result.size()).isEqualTo(autoCount);
    }

    @Test
    @DisplayName("자동 로또만 생성되어야 한다 (수동 로또 없이, 개수 5개)")
    void generate_withEmptyManualLottos_shouldReturnOnlyAutoLottos() {
        List<Lotto> manualLottos = List.of();
        int autoCount = 5;

        List<Lotto> result = lottoGenerator.generate(manualLottos, autoCount);

        assertThat(result.size()).isEqualTo(autoCount);
    }

    @Test
    @DisplayName("수동 로또만 생성되어야 한다 (자동 로또 없이)")
    void generate_withEmptyAutoCount_shouldReturnOnlyManualLottos() {
        List<Lotto> manualLottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)))
        );
        int autoCount = 0;

        List<Lotto> result = lottoGenerator.generate(manualLottos, autoCount);

        assertThat(result.size()).isEqualTo(manualLottos.size());
        assertThat(result).containsExactlyInAnyOrderElementsOf(manualLottos);
    }
}
