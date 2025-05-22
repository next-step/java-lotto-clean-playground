package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoTest {

    @Test
    @DisplayName("1부터 6까지의 유효한 로또 번호로 객체를 생성할 수 있다")
    void createLottoWithValidNumbers() {
        List<LottoNumber> numbers = convert(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getNumbers()).containsExactlyElementsOf(numbers);
    }

    @Test
    @DisplayName("로또 번호 개수가 6개가 아니면 예외가 발생한다")
    void invalidSizeThrowsException() {
        List<LottoNumber> invalidNumbers = convert(List.of(1, 2, 3));

        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 예외가 발생한다")
    void duplicateNumbersThrowsException() {
        List<LottoNumber> duplicated = convert(List.of(1, 2, 3, 4, 5, 5));

        assertThatThrownBy(() -> new Lotto(duplicated))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    private List<LottoNumber> convert(List<Integer> raw) {
        return raw.stream().map(LottoNumber::new).collect(toList());
    }
}
