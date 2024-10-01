

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import util.LottoNumberSeparator;

import java.util.ArrayList;
import java.util.List;

@DisplayName("지난 주 당첨 로또 번호 분리 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class WinningLottoNumberSeparatorTest {

    @Test
    @DisplayName("지난 주 당첨 로또 분리자에 따라 번호를 분리할 수 있다.")
    public void separateWinningLottoNumber() {
        //given
        final String lastWeekWinningLottoNumber = "1, 2, 3, 4, 5, 6";
        List<String> expected = new ArrayList<String>(List.of("1", "2", "3", "4", "5", "6"));

        //when
        List<String> result = LottoNumberSeparator.separateWinningLottoNumbers(lastWeekWinningLottoNumber);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
