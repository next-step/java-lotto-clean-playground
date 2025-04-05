package domain;

import domain.generator.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    static Stream<Arguments> provideInvalidLottoNumbers_Size() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5))
        );
    }

    static Stream<Arguments> provideInvalidLottoNumbers_Duplicates() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 5))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLottoNumbers_Size")
    @DisplayName("숫자가 6개가 아닐 경우 예외가 발생한다.")
    void validate_LottoNumber_Size_ThrowException(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();

        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLottoNumbers_Duplicates")
    @DisplayName("중복된 숫자가 있을 경우 예외가 발생한다.")
    void validate_LottoNumber_Duplicates_ThrowException(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();

        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 번호가 있습니다.");
    }

    private static class TestLottoGenerator implements LottoGenerator {
        private static final List<LottoNumber> FIXED_NUMBERS = List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        @Override
        public Lotto generate() {
            return new Lotto(FIXED_NUMBERS);
        }

        @Override
        public List<Lotto> generateLottoList(int count) {
            return Collections.nCopies(count, generate());
        }
    }

    @Test
    @DisplayName("Lotto 객체 생성 시, 크기 6인 리스트가 생성되어야 한다.")
    void lotto_creation_test() {
        LottoGenerator generator = new TestLottoGenerator();
        Lotto lotto = generator.generate();

        assertNotNull(lotto.numbers());
        assertEquals(6, lotto.numbers().size());
    }

    @Test
    @DisplayName("Lotto 객체의 번호가 정상적으로 생성되어야 한다.")
    void lotto_number_creation_test() {
        Lotto lotto = new TestLottoGenerator().generate();

        List<LottoNumber> expectedNumbers = List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        assertEquals(expectedNumbers, lotto.numbers());
    }
}
