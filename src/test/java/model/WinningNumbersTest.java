package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static model.LottoFixture.DEFAULT_WINNING_NUMBERS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("model.WinningNumbers 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class WinningNumbersTest {
    private WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        //given
        winningNumbers = new WinningNumbers(DEFAULT_WINNING_NUMBERS);
    }

    @Test
    void BonusBall이_WinningNumber와_겹치면_예외가_발생한다() {
        //given
        LottoNumber duplicatedBonusBall = new LottoNumber(1);

        //when & then
        assertThatThrownBy(() -> winningNumbers.setBonusBall(duplicatedBonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void setBonusBall_호출_시_보너스_볼이_중복되지_않으면_성공한다() {
        //given
        LottoNumber validBonusBall = new LottoNumber(7);

        //when & then
        assertDoesNotThrow(() -> winningNumbers.setBonusBall(validBonusBall));
    }

    @Test
    void WinningNumbers_생성_시_숫자가_6개가_아니면_예외가_발생한다() {
        //given
        List<LottoNumber> numbersWithFive = DEFAULT_WINNING_NUMBERS.subList(0, 5);

        //when & then
        assertThatThrownBy(() -> new WinningNumbers(numbersWithFive))
                .isInstanceOf(IllegalArgumentException.class);    }

    @Test
    void WinningNumbers_생성_시_중복된_숫자가_있으면_예외가_발생한다() {
        //given
        List<LottoNumber> numbersWithDuplicates = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(5)
        );

        //when & then
        assertThatThrownBy(() -> new WinningNumbers(numbersWithDuplicates))
                .isInstanceOf(IllegalArgumentException.class);    }
}
