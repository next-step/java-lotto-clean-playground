package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.Lotto.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoWithDuplicateNumbers() {
        List<LottoNumber> duplicateNumbers = List.of(
                new LottoNumber(3),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(6),
                new LottoNumber(7),
                new LottoNumber(10)
        );

        assertThatThrownBy(() -> new Lotto(duplicateNumbers))
                .isInstanceOf(LottoException.DuplicateNumber.class);
    }

    @DisplayName("생성된 로또 번호는 오름차순으로 정렬되어 있다.")
    @Test
    void makeLottoSorted() {
        LottoMaker lottoMaker = new LottoMaker();

        Lotto lotto = lottoMaker.makeLotto();
        List<LottoNumber> numbers = lotto.numbers();

        assertThat(numbers).isSorted();
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

        Lottos lottos = new Lottos(List.of(lotto1));
        int totalPrice = 1000;

        LottoReceipt receipt = new LottoReceipt(lottos, totalPrice);

        assertThat(receipt.lottos().getLottos()).hasSize(1);
        assertThat(receipt.totalPrice()).isEqualTo(1000);
    }

    @DisplayName("2등(5개 일치 + 보너스 볼) 당첨 시 정확한 결과를 반환한다.")
    @Test
    void calculateBonusResult() {
        Lotto winningLotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        LottoNumber bonusNumber = new LottoNumber(7);

        Lotto userLotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(7)
        ));

        Lottos lottos = new Lottos(List.of(userLotto));
        LottoReceipt receipt = new LottoReceipt(lottos, 1000);

        LottoDraw draw = new LottoDraw(winningLotto, bonusNumber, receipt);

        assertThat(draw.getCount(LottoResult.BONUS)).isEqualTo(1);
        assertThat(draw.getCount(LottoResult.FIVE)).isEqualTo(0);
    }

    @DisplayName("당첨 개수와 보너스 일치 여부에 따라 올바른 결과를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "6, false, SIX",
            "6, true, SIX",
            "5, true, BONUS",
            "5, false, FIVE",
            "4, true, FOUR",
            "3, false, THREE",
            "2, true, NONE",
            "0, false, NONE"
    })
    void calculateLottoResult(int count, boolean matchBonus, LottoResult expected) {
        assertThat(LottoResult.valueOf(count, matchBonus)).isEqualTo(expected);
    }

    @DisplayName("입력 번호가 6개가 아니거나 형식이 틀리면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5",
            "1,2,3,4,5,6,7",
            "1, 2, 3, , 5, 6",
            "1;2;3;4;5;6",
            "one,2,3,4,5,6"
    })
    void parseInvalidInput(String input) {
        LottoParser lottoParser = new LottoParser();
        assertThatThrownBy(() -> lottoParser.parse(input))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("LottoResult의 값이 None일 경우 false를 제대로 반환한다.")
    @Test
    void isDisplayableBooleanTest() {
        LottoResult result = LottoResult.NONE;

        assertThat(result.isDisplayable()).isEqualTo(false);
    }
}
