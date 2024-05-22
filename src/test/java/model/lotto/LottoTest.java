package model.lotto;

import model.dice.Dice;
import model.dice.FakeDice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoTest {

    private Dice dice;

    @BeforeEach
    void init() {
        dice = new FakeDice();
    }

    @Test
    void 로또_정상_생성() {
        // when & then
        assertDoesNotThrow(() -> Lotto.createDefault(dice));
    }

    @Test
    void 로또_번호_확인() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.createDefault(dice);

        // when
        List<Integer> lottoNumbers = lotto.getNumbers();

        // then
        assertThat(lottoNumbers).isEqualTo(numbers);
    }
}
