import domain.CorrectLottoNumbersCheck;
import domain.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("로또 당첨 개수 체크 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CorrectLottoNumbersCheckTest {

    @Test
    @DisplayName("로또 당첨 개수를 체크하고 반환한다.")
    public void convertStringToInteger() {
        //given
        CorrectLottoNumbersCheck correctLottoNumbersCheck = new CorrectLottoNumbersCheck();

        Lotto lotto = new Lotto(new ArrayList<Integer>(List.of(7, 2, 19, 4, 5, 54)));
        Lotto winningLotto = new Lotto(new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6)));
        final int expected = 3;

        //when
        final int result = correctLottoNumbersCheck.checkCorrectLottoNumbers(winningLotto, lotto);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
