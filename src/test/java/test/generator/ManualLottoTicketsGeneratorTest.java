package test.generator;

import domain.Lotto;
import generator.ManualLottoTicketsGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ManualLottoTicketsGeneratorTest {

    @Test
    void 문자열리스트를_전달하면_수동로또티켓리스트가_생성된다() {
        // given
        List<String> lottoNumberLines = List.of(
                "1,2,3,4,5,6",
                "7,8,9,10,11,12"
        );

        // when
        List<Lotto> manualTickets = ManualLottoTicketsGenerator.createManualTickets(lottoNumberLines);

        // then
        assertSoftly(softly -> {
            softly.assertThat(manualTickets).hasSize(2);
            softly.assertThat(manualTickets.get(0).getNumbers())
                    .containsExactly(1, 2, 3, 4, 5, 6);
            softly.assertThat(manualTickets.get(1).getNumbers())
                    .containsExactly(7, 8, 9, 10, 11, 12);
        });
    }

    @Test
    void 잘못된형식의문자열이면_예외가발생한다() {
        // given
        List<String> lottoNumberLines = List.of("1,2,a,b,3,4");

        // when & then
        assertThrows(
                NumberFormatException.class,
                () -> ManualLottoTicketsGenerator.createManualTickets(lottoNumberLines)
        );
    }
}
