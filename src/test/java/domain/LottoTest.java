package domain;

import constant.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LottoTest {
    @Test
    void 정상적인_입력에_대해_로또가_성공적으로_생성된다() {
        //given
        List<Integer> validNumbers = List.of(45, 2, 18, 7, 9, 6);
        List<Integer> expectedNumbers = List.of(2, 6, 7, 9, 18, 45);
        //when
        Lotto lotto = new Lotto(validNumbers);
        //then
        assertThat(lotto.lottoNumbers())
                .isEqualTo(expectedNumbers);
    }

    @Test
    void 로또_당첨_번호를_계산한다() {
        //given
        Lotto lotto = new Lotto(List.of(45, 2, 18, 7, 9, 6));
        List<Integer> winningNumbers = List.of(2, 6, 7, 13, 14, 15);
        //when
        int matchCount = lotto.countMatchingNumbers(winningNumbers);
        //then
        assertThat(matchCount)
                .isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 7, 15, 45})
    void 로또_번호_개수가_6개가_아니라면_예외가_발생한다(int num) {
        //given
        List<Integer> invalidSizedNumbers = IntStream.rangeClosed(1, num).boxed().toList();
        //when
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(invalidSizedNumbers)
        );
        //then
        assertThat(e.getMessage())
                .isEqualTo(ErrorMessage.INVALID_LOTTO_COUNT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 100, 1000})
    void 로또_번호가_1보다_작거나_45보다_크면_예외가_발생한다(int num) {
        //given
        List<Integer> tooSmallNumbers = List.of(1, 2, 3, 4, 5, 1 - num);
        List<Integer> tooBigNumbers = List.of(1, 2, 3, 4, 5, 45 + num);
        //when
        IllegalArgumentException e1 = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(tooSmallNumbers)
        );
        IllegalArgumentException e2 = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(tooBigNumbers)
        );
        //then
        assertAll(
                () -> assertThat(e1.getMessage())
                        .isEqualTo(ErrorMessage.INVALID_LOTTO_RANGE.getMessage()),
                () -> assertThat(e2.getMessage())
                        .isEqualTo(ErrorMessage.INVALID_LOTTO_RANGE.getMessage())
        );
    }

    @Test
    void 로또에_중복된_숫자가_존재하면_예외가_발생한다() {
        //given
        List<Integer> duplicateNumbers = List.of(1, 2, 3, 4, 5, 1);
        //when
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(duplicateNumbers)
        );
        //then
        assertThat(e.getMessage())
                .isEqualTo(ErrorMessage.INVALID_DUPLICATE_NUMBER.getMessage());
    }
}
