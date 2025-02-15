import domain.Lotto;
import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("로또 테스트")
public class TestLotto {

    @Nested
    @DisplayName("로또 기능 테스트")
    class TestLottoFeatures{

        @Test
        @DisplayName("로또 생성자 테스트")
        void testLottoConstructor(){
            //자동 생성자
            Lotto autoLotto = new Lotto();
            List<LottoNumber> numbers = autoLotto.getNumbers();

            // 로또 번호 갯수 테스트
            assertThat(numbers).hasSize(6);

            // 범위 테스트
            for (LottoNumber number : numbers) {
                assertThat(number.getNumber()).isBetween(1, 45);
            }

            //중복 테스트
            assertThat(numbers.stream().distinct().count()).isEqualTo(6);
        }

        @Test
        @DisplayName("로또 생성자 테스트 - 수동 생성 (정상)")
        void testLottoConstructorManualValid() {
            //given
            List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
            Lotto manualLotto = new Lotto(new ArrayList<>(inputNumbers));
            List<LottoNumber> numbers = manualLotto.getNumbers();

            // when
            assertThat(numbers).hasSize(6);
            for (int i = 0; i < 6; i++) {
                assertThat(numbers.get(i).getNumber()).isEqualTo(inputNumbers.get(i));
            }
        }

        @Test
        @DisplayName("로또 생성자 테스트 - 수동 생성 (잘못된 갯수)")
        void testLottoConstructorManualInvalidSize() {
            // 6개가 아닌 경우 (적은 경우)
            List<Integer> inputNumbersLess = Arrays.asList(1, 2, 3, 4, 5);
            assertThatThrownBy(() -> new Lotto(new ArrayList<>(inputNumbersLess)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 6개여야 합니다.");

            // 6개가 아닌 경우 (많은 경우)
            List<Integer> inputNumbersMore = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
            assertThatThrownBy(() -> new Lotto(new ArrayList<>(inputNumbersMore)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("로또 생성자 테스트 - 수동 생성 (잘못된 범위)")
        void testLottoConstructorManualInvalidRange() {
            // 범위를 벗어나는 경우 (작은 값)
            List<Integer> inputNumbersSmall = Arrays.asList(0, 2, 3, 4, 5, 6);
            assertThatThrownBy(() -> new Lotto(new ArrayList<>(inputNumbersSmall)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.");

            // 범위를 벗어나는 경우 (큰 값)
            List<Integer> inputNumbersLarge = Arrays.asList(1, 2, 3, 4, 5, 46);
            assertThatThrownBy(() -> new Lotto(new ArrayList<>(inputNumbersLarge)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        @Test
        @DisplayName("로또 생성자 테스트 - 수동 생성 (중복)")
        void testLottoConstructorManualDuplicate() {
            List<Integer> inputNumbersDuplicate = Arrays.asList(1, 2, 3, 4, 5, 5); // 5가 중복
            assertThatThrownBy(() -> new Lotto(new ArrayList<>(inputNumbersDuplicate)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 중복될 수 없습니다.");
        }

        @Test
        @DisplayName("로또 번호 유효성 검사 테스트")
        void testLottoNumberValidation(){
            //정상적인 로또 번호
            assertThatNoException().isThrownBy(()-> new LottoNumber(1));
            assertThatNoException().isThrownBy(()-> new LottoNumber(45));

            //비정상적인 로또 번호
            assertThatIllegalArgumentException().isThrownBy(()->new LottoNumber(0));
            assertThatIllegalArgumentException().isThrownBy(()->new LottoNumber(46));

        }
    }
}
