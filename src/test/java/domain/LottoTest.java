package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    @DisplayName("로또 생성 테스트")
    void lottoTest() {
        // Given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
    
        // When & Then
        assertThat(lotto.getNumbers()).isEqualTo(expected);
    }
}
