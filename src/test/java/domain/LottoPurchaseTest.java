package domain;

import factory.LottoGeneratorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LottoPurchaseTest {

    private LottoGeneratorFactory generatorFactory;

    @BeforeEach
    void setUp() {
        generatorFactory = new LottoGeneratorFactory();
    }

    private static Stream<Arguments> provideManualLotto() {
        return Stream.of(
                Arguments.of(List.of(new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))))),
                Arguments.of(List.of(new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9), new LottoNumber(10), new LottoNumber(11), new LottoNumber(12))))),
                Arguments.of(List.of(new Lotto(List.of(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15), new LottoNumber(16), new LottoNumber(17), new LottoNumber(18)))))
        );
    }

    @ParameterizedTest
    @MethodSource("provideManualLotto")
    @DisplayName("수동 로또 수와 자동 로또 수를 합친 개수가 정확한지 검증")
    void purchase_Lotto_Test_With_ManualLotto(List<Lotto> manualLottoList) {
        LottoPurchase lottoPurchase = new LottoPurchase(5000, manualLottoList, generatorFactory);
        // 총 구매한 로또 개수 확인 (수동 로또 수 + 자동 로또 수)
        assertEquals(manualLottoList.size() + 4, lottoPurchase.getLottoCount());
    }

    @Test
    @DisplayName("로또 가격은 1000원이다.")
    void price_Per_Lotto_Test() {
        assertEquals(1000, LottoPurchase.getPricePerLotto());
    }
}
