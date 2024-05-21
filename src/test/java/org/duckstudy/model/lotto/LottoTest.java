package org.duckstudy.model.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.Stream;
import org.duckstudy.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("로또 테스트")
class LottoTest {

    @Nested
    @DisplayName("로또 생성 테스트")
    class LottoCreationTest {

        @Test
        @DisplayName("1 이상 45 이하의 랜덤 숫자 6개를 가진 로또를 생성한다")
        void createRandomLottoSuccess() {
            Lotto lotto = Lotto.createRandomLotto();

            assertAll(
                    () -> assertThat(lotto.getLotto()).hasSize(6),
                    () -> assertThat(lotto.getLotto())
                            .allMatch(lottoNumber -> lottoNumber.getValue() >= 1 && lottoNumber.getValue() < 46)
            );
        }

        @Test
        @DisplayName("입력받은 수로 로또를 생성한다")
        void createManualLottoSuccess() {
            List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                    .map(LottoNumber::new)
                    .toList();

            assertThatCode(() -> new Lotto(lottoNumbers))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("로또를 생성할 때 6개의 숫자가 아니면 예외를 발생한다")
        void createManualLottoFailWhenSizeIsNotSix() {
            List<LottoNumber> lottoNumbers = List.of(
                    new LottoNumber(1)
            );

            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("로또를 생성할 때 중복된 숫자가 있으면 예외를 발생한다")
        void createManualLottoFailWhenDuplicateNumberExists() {
            List<LottoNumber> lottoNumbers = Stream.of(1, 1, 2, 3, 4, 5)
                    .map(LottoNumber::new)
                    .toList();

            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    @Nested
    @DisplayName("로또 계산 테스트")
    class LottoCalculationTest {

        @Test
        @DisplayName("로또 구매 금액을 입력하면 가격에 맞는 로또 개수를 계산한다")
        void calculateLottoCountWhenInputPrice() {
            Price price = new Price(10000);

            assertThat(Lotto.calculateLottoCount(price)).isEqualTo(10);
        }

        @Test
        @DisplayName("로또 당첨 번호를 입력하면 당첨 금액을 계산한다")
        void calculateWinningPrice() {
            assertThat(Lotto.calculateWinningPrice(3)).isEqualTo(new Price(5000));
        }
    }

    @Nested
    @DisplayName("로또 비교 테스트")
    class LottoComparisonTest {

        @Test
        @DisplayName("로또 번호 중 일치하는 숫자의 개수를 반환한다")
        void countMatchingNumber() {
            Lotto lotto = Stream.of(1, 2, 3, 4, 5, 6)
                    .map(LottoNumber::new)
                    .collect(collectingAndThen(toList(), Lotto::new));

            Lotto compareLotto = Stream.of(1, 2, 3, 7, 8, 9)
                    .map(LottoNumber::new)
                    .collect(collectingAndThen(toList(), Lotto::new));

            assertThat(lotto.countMatchingNumber(compareLotto)).isEqualTo(3);
        }
    }
}
