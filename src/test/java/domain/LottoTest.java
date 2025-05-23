package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Nested
    @DisplayName("Lotto 도메인 제약 테스트")
    class LottoValidateTest {

        @Test
        @DisplayName("6개의 LottoNumber로 구성된 경우")
        void validate_로또번호6개인경우_예외를발생하지않는다() {

            List<LottoNumber> numbers = List.of(LottoNumber.of(1), LottoNumber.of(2),
                    LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                    LottoNumber.of(6));

            assertThatNoException().isThrownBy(() -> new Lotto(numbers));
        }

        @Test
        @DisplayName("6개보다 많은 LottoNumber로 구성된 경우")
        void validate_로또번호6개보다많은경우_예외발생한다() {

            List<LottoNumber> tooMuchNumbers = List.of(LottoNumber.of(1), LottoNumber.of(2),
                    LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                    LottoNumber.of(6), LottoNumber.of(7));

            assertThatThrownBy(() -> new Lotto(tooMuchNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 6개이어야 합니다.");
        }

        @Test
        @DisplayName("6개보다 적은 LottoNumber로 구성된 경우")
        void validate_로또번호6개보다적은경우_예외발생한다() {

            List<LottoNumber> notEnoughNumbers = List.of(LottoNumber.of(1), LottoNumber.of(2),
                    LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5));

            assertThatThrownBy(() -> new Lotto(notEnoughNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 6개이어야 합니다.");
        }

        @Test
        @DisplayName("중복있는 LottoNumber로 구성된 경우")
        void validate_로또번호중복있는경우_예외발생한다() {

            List<LottoNumber> duplicatedNumbers = List.of(LottoNumber.of(1), LottoNumber.of(1),
                    LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),LottoNumber.of(4));

            assertThatThrownBy(() -> new Lotto(duplicatedNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 중복될 수 없습니다.");
        }

    }

    @Nested
    @DisplayName("Lotto 조회 기능 테스트")
    class ReadLottoNumberTest {

        @Test
        @DisplayName("getNumbers() 읽기전용인지 테스트")
        void getNumbers_getNumbers로가져온후변형을시도하면_오류가난다() {

            List<LottoNumber> numbers = List.of(LottoNumber.of(1), LottoNumber.of(2),
                    LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                    LottoNumber.of(6));

            Set<LottoNumber> gotLottoNumbers = new Lotto(numbers).getNumbers();


            assertThatThrownBy(() -> gotLottoNumbers.add(LottoNumber.of(7)))
                    .isInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        @DisplayName("contains()에 넘겨준 LottoNumber 일치하는 경우")
        void contains_Lotto가해당LottoNumber가지고있으면_true반환() {
            List<LottoNumber> numbers = List.of(LottoNumber.of(1), LottoNumber.of(2),
                    LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                    LottoNumber.of(6));

            assertThat(new Lotto(numbers).contains(LottoNumber.of(4)))
                    .isTrue();
        }

        @Test
        @DisplayName("contains()에 넘겨준 LottoNumber 불일치하는 경우")
        void contains_Lotto가해당LottoNumber가지고있지않으면_false반환() {
            List<LottoNumber> numbers = List.of(LottoNumber.of(1), LottoNumber.of(2),
                    LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                    LottoNumber.of(6));

            assertThat(new Lotto(numbers).contains(LottoNumber.of(10)))
                    .isFalse();
        }

        @Test
        @DisplayName("getSortedNumbers() 정렬되었는지 테스트")
        void getSortedNumbers_오름차순으로정렬되어있어야한다() {
            List<LottoNumber> numbers = List.of(LottoNumber.of(45), LottoNumber.of(33),
                    LottoNumber.of(3), LottoNumber.of(7), LottoNumber.of(5),
                    LottoNumber.of(6));

            List<LottoNumber> sortedNumbers = new Lotto(numbers).getSortedNumbers();


            assertThat(sortedNumbers).extracting(LottoNumber::getLottoNumber)
                    .containsExactly(3, 5, 6, 7, 33, 45);
        }
    }
}
