package Model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoPriceTest {

    @Test
    void 가격이_1000원_단위어야한다(){
        int price = 1557;

        Assertions.assertThatThrownBy(() -> {
            LottoPrice price1 = new LottoPrice(price);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 가격이_0원_미만이면_안된다(){
        int price = -10;

        Assertions.assertThatThrownBy(() -> {
            LottoPrice price1 = new LottoPrice(price);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
