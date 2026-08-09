import domain.Lotto;
import domain.LottoNumber;
import domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    @DisplayName("보너스 번호와 당첨 번호가 중복")
    void duplicateBonusBall() {
        List<LottoNumber> numbers = new ArrayList<>();

        numbers.add(LottoNumber.from(1));
        numbers.add(LottoNumber.from(2));
        numbers.add(LottoNumber.from(3));
        numbers.add(LottoNumber.from(4));
        numbers.add(LottoNumber.from(5));
        numbers.add(LottoNumber.from(6));

        Lotto lotto = Lotto.from(numbers);
        LottoNumber bonusBall = LottoNumber.from(6);

        assertThrows(IllegalArgumentException.class, () -> WinningLotto.from(lotto, bonusBall));
    }
    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되지 않으면 예외 X")
    void validBonusBall() {
        List<LottoNumber> numbers = new ArrayList<>();

        numbers.add(LottoNumber.from(1));
        numbers.add(LottoNumber.from(2));
        numbers.add(LottoNumber.from(3));
        numbers.add(LottoNumber.from(4));
        numbers.add(LottoNumber.from(5));
        numbers.add(LottoNumber.from(6));

        Lotto lotto = Lotto.from(numbers);
        LottoNumber bonusBall = LottoNumber.from(7);

        assertDoesNotThrow(() -> WinningLotto.from(lotto, bonusBall));
    }
}
