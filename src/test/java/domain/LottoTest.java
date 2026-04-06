package domain;

import constant.ErrorMessage;
import dto.LottoStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void 정상적인_입력에_대해_로또가_성공적으로_생성된다() {
        //given
        List<LottoNumber> validNumbers = Stream.of(45, 2, 18, 7, 9, 6)
                .map(LottoNumber::valueOf)
                .toList();
        LottoStatus expectedNumbers = new LottoStatus(
                Stream.of(2, 6, 7, 9, 18, 45)
                        .map(LottoNumber::valueOf)
                        .toList()
        );
        //when
        Lotto lotto = new Lotto(validNumbers);
        //then
        assertThat(lotto.getLottoStatus())
                .isEqualTo(expectedNumbers);
    }

    @Test
    void 로또_당첨_번호를_계산한다() {
        //given
        Lotto lotto = new Lotto(Stream.of(2, 6, 7, 9, 18, 45)
                .map(LottoNumber::valueOf)
                .toList());
        List<LottoNumber> winningNumbers = Stream.of(2, 6, 7, 13, 14, 15)
                .map(LottoNumber::valueOf)
                .toList();
        Lotto winningLotto = new Lotto(winningNumbers);
        //when
        int matchCount = lotto.countMatchingNumbers(winningLotto);
        //then
        assertThat(matchCount)
                .isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 7, 15, 45})
    void 로또_번호_개수가_6개가_아니라면_예외가_발생한다(int num) {
        //given
        List<LottoNumber> invalidSizedNumbers = IntStream.rangeClosed(1, num)
                .mapToObj(LottoNumber::valueOf)
                .toList();
        //when
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(invalidSizedNumbers)
        );
        //then
        assertThat(e.getMessage())
                .isEqualTo(ErrorMessage.INVALID_LOTTO_COUNT.getMessage());
    }

    @Test
    void 로또에_중복된_숫자가_존재하면_예외가_발생한다() {
        //given
        List<LottoNumber> duplicateNumbers = Stream.of(1, 2, 3, 4, 5, 1)
                .map(LottoNumber::valueOf)
                .toList();
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
