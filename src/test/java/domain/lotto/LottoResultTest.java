package domain.lotto;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static domain.util.LottoUtil.createLottoTicket;

class LottoResultTest {

    @ParameterizedTest
    @MethodSource(value = "test")
    void 당첨_찾기(LottoResult lottoResult, LottoTicket lottoTicket, Prize resultPrize) {

        Prize prize = lottoResult.findPrize(lottoTicket);
        Assertions.assertThat(prize).isEqualTo(resultPrize);
    }

    private static Stream<Arguments> test() {
        return Stream.of(
            Arguments.arguments(
                new LottoResult(createLottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(15)),
                createLottoTicket(1, 2, 3, 4, 5, 6),
                Prize.FIRST
            ),
            Arguments.arguments(
                new LottoResult(createLottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(45)),
                createLottoTicket(1, 2, 3, 4, 5, 45),
                Prize.SECOND_BONUS_BALL
            ),
            Arguments.arguments(
                new LottoResult(createLottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(15)),
                createLottoTicket(1, 2, 3, 4, 5, 45),
                Prize.SECOND
            ),
            Arguments.arguments(
                new LottoResult(createLottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(15)),
                createLottoTicket(1, 2, 3, 4, 44, 45),
                Prize.THIRD
            ),
            Arguments.arguments(
                new LottoResult(createLottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(15)),
                createLottoTicket(1, 2, 3, 43, 44, 45),
                Prize.FOURTH
            ),
            Arguments.arguments(
                new LottoResult(createLottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(15)),
                createLottoTicket(1, 2, 42, 43, 44, 45),
                Prize.LOSING_TICKET
            )
        );
    }
}
