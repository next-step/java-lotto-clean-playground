package domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class LottoTest {
    @Test
    @DisplayName("create lotto successfully")
    void createLottoSuccessfully() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList();
        assertThatCode(() -> new Lotto(lottoNumbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("lotto size is not 6")
    void exceptionSize() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5)
                .map(LottoNumber::new)
                .toList();
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("lotto numbers are duplicated")
    void exceptionDuplicated() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 6, 6)
                .map(LottoNumber::new)
                .toList();
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("count matching numbers")
    void countMatchingNumbers() {
        List<LottoNumber> numbers1 = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList();
        Lotto lottoA = new Lotto(numbers1);

        List<LottoNumber> numbers2 = Stream.of(4, 5, 6, 7, 8, 9)
                .map(LottoNumber::new)
                .toList();
        Lotto lotto = new Lotto(numbers2);

        int matchCount = lottoA.countMatchingNumbers(lotto);

        assertThat(matchCount).isEqualTo(3);
    }
}
