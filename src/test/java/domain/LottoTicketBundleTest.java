package domain;

import domain.wrappers.LottoPayment;
import domain.wrappers.LottoResult;
import domain.wrappers.TicketCount;
import number_generator.LottoNumberListGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class LottoTicketBundleTest {

    @DisplayName("당첨 번호 조합에 따라 3~6개 일치 당첨 횟수가 정확히 계산된다.")
    @ParameterizedTest
    @MethodSource("winnerTicketAndLottoResultMethodSource")
    void lottoResulTest(List<LottoNumber> winnerNumbers, List<Integer> expectedResult) {
        // Given
        LottoNumberListGenerator from1to6NumberListGenerator = new From1to6NumberListGenerator();
        LottoTicketBundle bundle = new LottoTicketBundle();
        bundle.createRandomTickets(new TicketCount(new LottoPayment(1000)), from1to6NumberListGenerator);
        LottoTicket winnerTicket = new  LottoTicket(winnerNumbers);

        // When
        LottoResult result = bundle.createLottoResult(winnerTicket);

        // Then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(result.getThreeCorrectCount()).isEqualTo(expectedResult.get(0)),
                () -> Assertions.assertThat(result.getFourCorrectCount()).isEqualTo(expectedResult.get(1)),
                () -> Assertions.assertThat(result.getFiveCorrectCount()).isEqualTo(expectedResult.get(2)),
                () -> Assertions.assertThat(result.getSixCorrectCount()).isEqualTo(expectedResult.get(3))
        );
    }

    private static class From1to6NumberListGenerator implements LottoNumberListGenerator {
        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }


    private static Stream<Arguments> winnerTicketAndLottoResultMethodSource() {
        return Stream.of(
                Arguments.of(List
                                .of(1, 2, 3, 40, 41, 42)
                                .stream()
                                .map(LottoNumber::new)
                                .toList(),
                        List.of(1, 0, 0, 0)),
                Arguments.of(List.of(1, 2, 3, 4, 41, 42)
                                .stream()
                                .map(LottoNumber::new)
                                .toList(),
                        List.of(0, 1, 0, 0)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 42)
                                .stream()
                                .map(LottoNumber::new)
                                .toList(),
                        List.of(0, 0, 1, 0)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6)
                                .stream()
                                .map(LottoNumber::new)
                                .toList(),
                        List.of(0, 0, 0, 1))
        );
    }
}
