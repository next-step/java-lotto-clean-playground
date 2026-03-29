package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    void 로또_목록을_번호_리스트로_변환한다() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        ));

        List<List<Integer>> numberLists = lottos.toNumberLists();

        assertThat(numberLists).isEqualTo(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        ));
    }
}
