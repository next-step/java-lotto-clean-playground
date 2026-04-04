package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LottoTest {
    
    @DisplayName("1보다 작은 숫자로 생성 시 IllegalArgumentException이 발생한다.")
    @Test
    void createLottoNumberUnderRange() {
        int underRangeNumber = 0;

        assertThatThrownBy(() -> new LottoNumber(underRangeNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자가 1보다 작습니다.");
    }

    @DisplayName("45보다 큰 숫자로 생성 시 IllegalArgumentException이 발생한다.")
    @Test
    void createLottoNumberOverRange() {
        int overRangeNumber = 46;

        assertThatThrownBy(() -> new LottoNumber(overRangeNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자가 45보다 큽니다.");
    }

    @DisplayName("중복된 숫자가 있으면 DuplicateNumber 예외가 발생한다.")
    @Test
    void createLottoWithDuplicateNumbers() {
        List<LottoNumber> duplicateNumbers = new ArrayList<>();
        duplicateNumbers.add(new LottoNumber(3));
        duplicateNumbers.add(new LottoNumber(3));
        duplicateNumbers.add(new LottoNumber(4));
        duplicateNumbers.add(new LottoNumber(6));
        duplicateNumbers.add(new LottoNumber(7));
        duplicateNumbers.add(new LottoNumber(10));


        assertThatThrownBy(() -> new Lotto(duplicateNumbers))
                .isInstanceOf(Lotto.LottoException.DuplicateNumber.class)
                .hasMessage("중복된 숫자가 있습니다.");
    }

    @DisplayName("생성된 로또 번호는 오름차순으로 정렬되어 있다.")
    @Test
    void makeLottoSorted() {
        LottoMaker lottoMaker = new LottoMaker();

        Lotto lotto = lottoMaker.makeLotto();
        List<LottoNumber> numbers = lotto.numbers();


        assertThat(numbers).isSorted();
    }

    @DisplayName("생성된 로또 번호는 모두 1에서 45 사이의 숫자여야 한다.")
    @Test
    void makeLottoRangeCheck() {
        LottoMaker lottoMaker = new LottoMaker();
        Lotto lotto = lottoMaker.makeLotto();
        
        assertThat(lotto.numbers()).allSatisfy(number -> {
            assertThat(number.number()).isBetween(1, 45);
        });
    }

    @DisplayName("로또 리스트와 총 가격을 포함하는 영수증을 생성할 수 있다.")
    @Test
    void createLottoReceipt() {
        Lotto lotto1 = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        Lotto lotto2 = new Lotto(List.of(
                new LottoNumber(10),
                new LottoNumber(11),
                new LottoNumber(12),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15)
        ));

        List<Lotto> lottos = List.of(lotto1, lotto2);
        int totalPrice = 2000;

        LottoReceipt receipt = new LottoReceipt(lottos, totalPrice);

        assertThat(receipt.lottoRows()).hasSize(2);
        assertThat(receipt.totalPrice()).isEqualTo(2000);

        assertThat(receipt.lottoRows().get(0).numbers())
                .extracting(LottoNumber::number)
                .containsExactly(1, 2, 3, 4, 5, 6);

        assertThat(receipt.lottoRows()).contains(lotto2);
    }

    @DisplayName("수익률을 정확하게 계산한다.")
    @Test
    void calculateRateOfReturn() {
        Lotto drawnLotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        Lotto purchasedLotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(7),
                new LottoNumber(8),
                new LottoNumber(9)
        ));

        List<Lotto> purchasedLottos = List.of(purchasedLotto);
        LottoReceipt receipt = new LottoReceipt(purchasedLottos, 10000);

        LottoDraw draw = new LottoDraw(drawnLotto, receipt);
        float rateOfReturn = draw.getRateOfReturn();

        assertThat(rateOfReturn).isEqualTo(5000f / 10000f);
    }
}
