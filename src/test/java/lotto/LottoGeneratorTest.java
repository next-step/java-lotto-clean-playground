package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    @DisplayName("유효한 로또 한 장을 생성한다")
    void generateLotto() {
        Lotto lotto = LottoGenerator.generateLotto();

        long lottoNumberCount = IntStream.range(1, 46)
                .mapToObj(LottoNumber::new)
                .filter(lotto::contains)
                .count();

        assertThat(lottoNumberCount).isEqualTo(6);
    }
}
