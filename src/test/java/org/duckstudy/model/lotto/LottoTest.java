package org.duckstudy.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("로또 테스트")
class LottoTest {

    @Nested
    @DisplayName("로또 생성 테스트")
    class LottoCreationTest {

        @Nested
        @DisplayName("리스트로 입력받은 로또 생성 테스트")
        class LottoCreationWithListTest {

            @Test
            @DisplayName("로또를 생성한다")
            void createLottoByListSuccess() {
                List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::valueOf)
                        .toList();

                assertThatCode(() -> new Lotto(lottoNumbers))
                        .doesNotThrowAnyException();
            }

            @Test
            @DisplayName("로또를 생성할 때 6개의 로또 번호가 아니면 예외를 발생한다")
            void createLottoByListFailWhenSizeIsNotSix() {
                List<LottoNumber> lottoNumbers = List.of(
                        LottoNumber.valueOf(1)
                );

                assertThatThrownBy(() -> new Lotto(lottoNumbers))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("로또 번호는 6개여야 합니다.");
            }

            @Test
            @DisplayName("로또를 생성할 때 중복된 로또 번호가 있으면 예외를 발생한다")
            void createLottoByListFailWhenDuplicateNumberExists() {
                List<LottoNumber> lottoNumbers = Stream.of(1, 1, 2, 3, 4, 5)
                        .map(LottoNumber::valueOf)
                        .toList();

                assertThatThrownBy(() -> new Lotto(lottoNumbers))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("로또 번호는 중복되지 않아야 합니다.");
            }
        }

        @Nested
        @DisplayName("배열로 입력받은 로또 생성 테스트")
        class LottoCreationWithArrayTest {

            @Test
            @DisplayName("로또를 생성한다")
            void createLottoByListSuccess() {
                int[] lottoNumbers = {1, 2, 3, 4, 5, 6};

                assertThatCode(() -> new Lotto(lottoNumbers))
                        .doesNotThrowAnyException();
            }

            @Test
            @DisplayName("로또를 생성할 때 6개의 로또 번호가 아니면 예외를 발생한다")
            void createLottoByListFailWhenSizeIsNotSix() {
                int[] lottoNumbers = {1};

                assertThatThrownBy(() -> new Lotto(lottoNumbers))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("로또 번호는 6개여야 합니다.");
            }

            @Test
            @DisplayName("로또를 생성할 때 중복된 로또 번호가 있으면 예외를 발생한다")
            void createLottoByListFailWhenDuplicateNumberExists() {
                int[] lottoNumbers = {1, 1, 3, 4, 5, 6};

                assertThatThrownBy(() -> new Lotto(lottoNumbers))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("로또 번호는 중복되지 않아야 합니다.");
            }
        }
    }

    @Nested
    @DisplayName("로또 비교 테스트")
    class LottoComparisonTest {

        @Test
        @DisplayName("일치하는 로또 번호의 개수를 반환한다")
        void countMatchingNumber() {
            Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
            Lotto compareLotto = new Lotto(1, 2, 3, 7, 8, 9);

            assertThat(lotto.countMatchingNumber(compareLotto)).isEqualTo(3);
        }
    }
}
