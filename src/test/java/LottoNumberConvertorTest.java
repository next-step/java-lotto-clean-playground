import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import util.LottoNumberConvertor;

import java.util.ArrayList;
import java.util.List;

@DisplayName("로또 변환 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoNumberConvertorTest {

    @Test
    @DisplayName("문자열 로또 리스트를 정수 로또 리스트로 변환할 수 있다.")
    public void convertStringToInteger() {
        //given
        String lottoNumbers = "1, 2, 3, 4, 5, 6";
        List<Integer> expected = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6));

        //when
        List<Integer> result = LottoNumberConvertor.convertLottoNumbers(lottoNumbers);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
