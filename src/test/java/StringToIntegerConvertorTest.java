import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import util.StringToIntegerConvertor;
import util.WinningLottoNumberSeparator;

import java.util.ArrayList;
import java.util.List;

@DisplayName("문자열 로또 리스트에서 정수 로또 리스트로의 변환 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StringToIntegerConvertorTest {

    @Test
    @DisplayName("문자열 로또 리스트를 정수 로또 리스트로 변환할 수 있다.")
    public void convertStringToInteger() {
        //given
        List<String> lottoNumbers = new ArrayList<String>(List.of("1", "2", "3", "4", "5", "6"));
        List<Integer> expected = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6));

        //when
        List<Integer> result = StringToIntegerConvertor.convertStringToInteger(lottoNumbers);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

}
