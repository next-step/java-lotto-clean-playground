package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("model.WinningNumbers 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class WinningNumbersTest {

    @Test
    void WinningNumbers_생성_시_숫자가_6개가_아니면_예외가_발생한다() {
        List<LottoNumber> numbersWithFive = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5)
        );

        assertThrows(IllegalArgumentException.class, () -> new WinningNumbers(numbersWithFive));
    }

    @Test
    void WinningNumbers_생성_시_중복된_숫자가_있으면_예외가_발생한다() {
        List<LottoNumber> numbersWithDuplicates = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(5)
        );

        assertThrows(IllegalArgumentException.class, () -> new WinningNumbers(numbersWithDuplicates));
    }
}
