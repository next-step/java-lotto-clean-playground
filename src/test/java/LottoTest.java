import model.Lotto;
import model.LottoMoney;
import model.LottoNumber;
import model.WinLotto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void lottoRangeTest() {
        String testNum = "46";
        assertThatThrownBy(() -> new LottoNumber(testNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이여야 합니다.");
    }


    @Test
    void lottoMoneyTest() {
        String price = "1001";
        assertThatThrownBy(() -> new LottoMoney(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 천원 단위로만 구입이 가능합니다.");
    }

    @Test
    void lottoListTest() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(number -> String.valueOf(number))
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개입니다.");
    }

    @Test
    void lottoDuplicateNumbersTest() {
        List<Integer> numbersWithDuplicates = List.of(1, 2, 3, 4, 4, 6);

        List<LottoNumber> lottoNumbers = numbersWithDuplicates.stream()
                .map(number -> String.valueOf(number))
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복이 불가합니다.");
    }

    @Test
    void isLottoNumber() {
        String invalidNumber = "십";

        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 숫자여야 합니다.");
    }

    @Test
    void isLottoPriceNumber() {
        String invalidAmount = "만원";

        assertThatThrownBy(() -> new LottoMoney(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가격은 숫자여야 합니다.");
    }

    @Test
    void testBonusBallDuplicated() {
        // given
        String inputWinnerLotto = "1, 2, 3, 4, 5, 6";
        List<Lotto> userLottos = new ArrayList<>();
        String bonusBall = "6";

        // when & then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WinLotto(inputWinnerLotto, userLottos, bonusBall));
        assertThat(exception.getMessage()).contains("보너스 볼은 중복이 불가합니다.");
    }


}
