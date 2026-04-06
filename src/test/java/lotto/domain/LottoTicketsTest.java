package lotto.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTicketsTest {

    @Test
    void 수동_번호와_자동_개수를_합쳐서_전체_티켓을_생성한다() {
        // given
        List<Lotto> manualTickets = Arrays.asList(
                Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Lotto.from(Arrays.asList(7, 8, 9, 10, 11, 12))
        );
        int autoCount = 3;

        // when
        LottoTickets totalTickets = LottoTickets.createCombined(manualTickets, autoCount);

        // then
        assertThat(totalTickets.getTickets()).hasSize(5);
    }
}
