import domain.Lotto;
import domain.LottoNumber;
import domain.LottoPrice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Nested
    class LottoNumberTest {
        @Test
        void 로또_번호는_1부터_45까지_이어야_한다() {
            int number = 46;

            assertThatThrownBy(() -> new LottoNumber(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    @Nested
    class LottoSizeTest {
        @Test
        void 생성된_로또한개는_6개의_번호를_가지고_있어야_한다() {
            Lotto lotto = new Lotto();

            int numberOfLottoNumbers = lotto.getLottoNumbers().size();

            assertThat(numberOfLottoNumbers).isEqualTo(6);
        }
    }

    @Test
    void 생성된_로또번호는_중복되지_않아야_한다() {
        Lotto lotto = new Lotto();

        List<Integer> numbers = lotto.getLottoNumbers();
        boolean hasDuplicates = false;

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i).equals(numbers.get(j))) {
                    hasDuplicates = true;
                    break;
                }
            }
        }
        assertThat(hasDuplicates).isFalse();
    }

}