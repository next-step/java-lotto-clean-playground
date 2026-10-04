package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("입력 순서와 무관하게 항상 오름차순으로 정렬된다")
    void 입력_순서와_무관하게_항상_오름차순으로_정렬된다() {
        Lotto lotto = new Lotto(List.of(6, 1, 4, 2, 5, 3));

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
