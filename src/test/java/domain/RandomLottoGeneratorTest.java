package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomLottoGeneratorTest {

    @Test
    @DisplayName("생성된 로또 번호는 6개이며 정렬되어 있다.")
    void generateTest() {
        //given
        RandomLottoGenerator generator = new RandomLottoGenerator();

        //when
        Lotto lotto = generator.generate();
        List<LottoNumber> numbers = lotto.getNumbers();

        //then
        assertThat(numbers).hasSize(6);
        assertThat(numbers).isSorted();
    }

    @Test
    @DisplayName("Integer 리스트를 Lotto 객체로 변환한다.")
    void parseLottoNumberTest() {
        //given
        RandomLottoGenerator generator = new RandomLottoGenerator();
        List<Integer> input = List.of(1, 10, 20, 30, 40, 45);

        //when
        Lotto lotto = generator.parseLottoNumber(input);

        //then
        assertThat(lotto.getNumbers())
                .extracting("number")
                .containsExactly(1, 10, 20, 30, 40, 45);
    }

    @Test
    @DisplayName("생성할 때마다 서로 다른 번호 조합을 가질 확률이 높다.")
    void randomnessTest() {
        //given
        RandomLottoGenerator generator = new RandomLottoGenerator();

        //when
        Lotto firstLotto = generator.generate();
        Lotto secondLotto = generator.generate();

        //then
        assertThat(firstLotto.getNumbers()).isNotEqualTo(secondLotto.getNumbers());
    }
}
