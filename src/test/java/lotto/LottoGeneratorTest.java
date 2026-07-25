package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    @DisplayName("중복 없이 1~45 중 6개의 번호를 오름차순 정렬하여 생성한다")
    void generateLottoNumbers() {
        List<Integer> lottoNumbers = LottoGenerator.generateNumbers();

        assertThat(lottoNumbers)
                .hasSize(6) // 숫자가 6개인지 확인
                .doesNotHaveDuplicates() // 중복된 숫자가 없는지 확인
                .allMatch(number -> number >= 1 && number <= 45) // 1~45의 숫자인지 확인
                .isSorted(); // 오름차순 정렬 확인
    }
}
