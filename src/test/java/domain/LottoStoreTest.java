package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.FixNumbersGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static fixture.LottoFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoStoreTest {

    private final LottoStore lottoStore = new LottoStore(new FixNumbersGenerator(testNumbersOneToSix));

    @ParameterizedTest
    @MethodSource("provideAmountAndCount")
    @DisplayName("OK : 구매 가능한 로또 개수를 반환한다.")
    void getLottoCount(long amount, int count) {
        assertThat(lottoStore.getLottoCount(amount)).isEqualTo(count);
    }

    @Test
    @DisplayName("OK : 수동 로또들과 자동 로또 개수를 입력 받아서 로또를 반환한다.")
    void buyLottosSize(){
        List<Lotto> lottos = List.of(testLottoOneToSix, testLottoSevenToTwelve, testLottoFortyToFortyFive);
        long automaticCount = 10L;
        LottoGroup lottoGroup = lottoStore.buyLottos(lottos, automaticCount);

        assertThat(lottoGroup.getLottos().size()).isEqualTo(automaticCount + lottos.size());
    }

    @Test
    @DisplayName("OK : 자동 로또 값이 올바르게 반환된다.")
    void buyLottosValue(){
        List<Lotto> lottos = new ArrayList<>();
        long automaticCount = 1L;
        LottoGroup lottoGroup = lottoStore.buyLottos(lottos, automaticCount);
        assertThat(lottoGroup.getLottos().get(0).getNumbers()).isEqualTo(testNumbersOneToSix);
    }

    private static Stream<Arguments> provideAmountAndCount(){
        return Stream.of(
                Arguments.of(0L, 0),
                Arguments.of(500L, 0),
                Arguments.of(1000L, 1),
                Arguments.of(1500L, 1),
                Arguments.of(10000L, 10)
        );
    }
}