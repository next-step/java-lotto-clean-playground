package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoPriceTest {
    @DisplayName("로또금액 생성 테스트 : 1000이상 1000원 단위 금액에서 정상 수행")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 40000, 99000})
    public void creationTest(int price) {
        LottoPrice lottoPrice = new LottoPrice(price, 1);
        assertThat(lottoPrice.price()).isEqualTo(price);
    }

    @DisplayName("로또금액 생성 테스트 : 범위 외의 수에 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 0, 999})
    public void outOfRangeTest(int price) {
        assertThatThrownBy(() -> new LottoPrice(price,1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또금액 생성 테스트 : 수동 로또 수 0 미만 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -999})
    public void outOfMinManualTest(int manualNumber) {
        assertThatThrownBy(() -> new LottoPrice(1000, manualNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또금액 생성 테스트 : 수동 로또 수 금액 초과 시 예외 발생")
    @Test
    public void outOfMaxManualTest() {
        assertThatThrownBy(() -> new LottoPrice(1000,2))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
