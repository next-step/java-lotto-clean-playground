package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CashierTest {
    @DisplayName("1000원 당 하나의 로또 티켓을 발급한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 3000, 10000, 600000})
    public void TestGenerateTickets(int price) {
        // given
        Cashier cashier = new Cashier(new RandomLottoTicketGenerator());

        // when
        int actual = cashier.generateTickets(new Price(price)).getNumberOfTickets();

        // then
        assertThat(new Count(actual)).isEqualTo(new Price(price).getBuyableLottoCount());
    }

    @DisplayName("로또 결과와 가격으로 수익률을 계산한다.")
    @ParameterizedTest
    @MethodSource
    public void testGetProfitRate(LottoResult result, Price price, double expected) {
        // given
        Cashier cashier = new Cashier(new RandomLottoTicketGenerator());

        // when
        double actual = cashier.getProfitRate(result, price);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetProfitRate() {
        return Stream.of(
                Arguments.arguments(new LottoResult(List.of(new Count(1), new Count(2), new Count(1), new Count(0))), new Price(10000), 160.5),
                Arguments.arguments(new LottoResult(List.of(new Count(0), new Count(0), new Count(0), new Count(0))), new Price(10000), 0.0),
                Arguments.arguments(new LottoResult(List.of(new Count(2), new Count(1), new Count(2), new Count(1))), new Price(10000), 200306.0)
        );
    }
}
