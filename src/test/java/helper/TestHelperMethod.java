package helper;

import domain.lotto.wrap.LottoNumber;
import domain.lotto.wrap.Money;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHelperMethod {

    public static InputView inputViewOf(String input) {
        return new InputView(new ByteArrayInputStream(input.getBytes()));
    }
    public static Money priceOf() {
            return new Money(1_000);
    }

    public static List<LottoNumber> toLottoNumbers(int... values) {
        return Arrays.stream(values)
                .mapToObj(LottoNumber::new)
                .toList();
    }
}
