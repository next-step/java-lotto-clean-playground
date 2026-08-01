package helper;

import domain.lotto.wrap.LottoNumber;
import domain.lotto.wrap.money.Money;
import domain.lotto.wrap.money.Payment;
import domain.lotto.wrap.money.Price;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

public class TestHelperMethod {

    public static InputView inputViewOf(String input) {
        return new InputView(new ByteArrayInputStream(input.getBytes()));
    }
    public static Price priceOf() {
            return new Price(1_000);
    }

    public static List<LottoNumber> toLottoNumbers(int... values) {
        return Arrays.stream(values)
                .mapToObj(LottoNumber::new)
                .toList();
    }
}
