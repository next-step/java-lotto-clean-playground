package fixed;

import domain.draw.DrawLottoNumber;
import domain.lotto.Lotto;
import domain.lotto.wrap.LottoNumber;

import java.util.Arrays;
import java.util.List;

public class FixedDrawLottoNumber implements DrawLottoNumber {

    private final List<LottoNumber> fixedNumbers;

    public FixedDrawLottoNumber(int... numbers) {
        this.fixedNumbers = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList();
    }

    @Override
    public Lotto draw() {
        return new Lotto(fixedNumbers);
    }
}
