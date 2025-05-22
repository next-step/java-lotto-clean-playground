package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumbersGeneratorTest {

    @Test
    @DisplayName("RandomNumberGenerator는 길이가 6인 숫자 리스트를 제공한다.")
    void testGeneratingSixNumbers() {
        assertThat(RandomNumbersGenerator.generateSixLottoNumbers())
                .hasSize(6);
    }

    @Test
    @DisplayName("RandomNumberGenerator는 1~45 사이의 숫자를 포함하는 리스트를 제공한다.")
    void testGeneratingNumbersBetween1To45() {
        for (int i = 0; i < 1000; i++) {
            assertThat(RandomNumbersGenerator.generateSixLottoNumbers())
                    .allMatch(number -> number >= 1 && number <= 45);
        }
    }
}
