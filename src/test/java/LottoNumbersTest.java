import domain.LottoNumber;
import domain.LottoNumbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class LottoNumbersTest {
    @Test
    void 로또_숫자는_총_6개여야한다() {
        List<LottoNumber> value = new ArrayList<>();
        value.add(new LottoNumber(1));
        value.add(new LottoNumber(2));
        value.add(new LottoNumber(3));
        value.add(new LottoNumber(4));
        value.add(new LottoNumber(5));
        value.add(new LottoNumber(6));

        Assertions.assertDoesNotThrow(() -> {
            LottoNumbers lottoNumbers = new LottoNumbers(value);
        });
    }

    @ParameterizedTest
    @MethodSource("methodSourceTestArguments")
    void 로또_숫자_6개가_아니면_예외발생(List<LottoNumber> value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LottoNumbers lottoNumbers = new LottoNumbers(value);
        });
    }

    private static Stream<Arguments> methodSourceTestArguments() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5)
                )),
                Arguments.arguments(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6),
                        new LottoNumber(7)
                ))

        );
    }

    @Test
    void 로또_숫자_6개에_중복_있을시_예외발생() {
        List<LottoNumber> value = new ArrayList<>();
        value.add(new LottoNumber(1));
        value.add(new LottoNumber(2));
        value.add(new LottoNumber(2));
        value.add(new LottoNumber(4));
        value.add(new LottoNumber(5));
        value.add(new LottoNumber(6));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LottoNumbers lottoNumbers = new LottoNumbers(value);
        });
    }
}
