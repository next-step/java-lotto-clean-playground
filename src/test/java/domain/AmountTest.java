package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.LottoConstant.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {

    @Test
    @DisplayName("OK : 정상적으로 금액을 입력 받는다.")
    void amount(){
        Amount amount = new Amount(1000L);
        assertThat(amount.getValue()).isEqualTo(1000L);
    }

    @ParameterizedTest
    @ValueSource(longs = {LOTTO_PRICE - 1L, -1000})
    @DisplayName("ERROR : 로또 금액보다 낮은 금액이면 에러가 발생한다.")
    void AmountLowerThanLottoPrice(long price){
        assertThrows(IllegalArgumentException.class, () -> new Amount(price));
    }
}