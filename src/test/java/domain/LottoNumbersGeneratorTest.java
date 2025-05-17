package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersGeneratorTest {
    @Test
    @DisplayName("로또 번호 생성 테스트")
    void generateLottoNumbers() {
        // Given
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        LottoNumbersGenerator mockLottoNumbersGenerator = mock(LottoNumbersGenerator.class);
        when(mockLottoNumbersGenerator.generate()).thenReturn(new LottoNumbers(expected.stream()
                .map(LottoNumber::of)
                .toList()));

        // When
        Lotto lotto = new Lotto(mockLottoNumbersGenerator.generate());

        // Then
        assertThat(lotto.getNumbers().getLottoNumbers().stream().map(LottoNumber::getNumber).toList()).isEqualTo(expected);
    }
}
