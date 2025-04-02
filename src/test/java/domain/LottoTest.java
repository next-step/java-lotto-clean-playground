package domain;

import domain.generator.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    private static class TestLottoGenerator implements LottoGenerator {
        @Override
        public Lotto generate() {
            return new Lotto(Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6)
            ));
        }
        @Override
        public List<Lotto> generateLottos(int count) {
            return IntStream.range(0, count)
                    .mapToObj(i -> generate())
                    .collect(Collectors.toList());
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
        LottoGenerator generator = new TestLottoGenerator();
        Lotto lotto = generator.generate();

        List<LottoNumber> expectedNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertEquals(expectedNumbers, lotto.numbers());
    }

    static Stream<Arguments> provideWinningNumbers() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        Arrays.asList(1, 2, 3, 4, 5, 6), 6 // 6개 모두 맞음
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        Arrays.asList(1, 2, 3, 7, 8, 9), 3 // 3개 맞음
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        Arrays.asList(10, 11, 12, 13, 14, 15), 0 // 꽝
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideWinningNumbers")
    @DisplayName("당첨 번호와 일치하는 개수를 올바르게 계산해야 한다.")
    void calculate_MatchCount_test(List<Integer> winningNums, int expectedMatchCount) {
        LottoGenerator generator = new TestLottoGenerator();
        Lotto lotto = generator.generate();

        List<LottoNumber> winningNumbers = winningNums.stream()
                .map(LottoNumber::new)
                .toList();

        int matchCount = lotto.calculateMatchCount(winningNumbers);

        assertEquals(expectedMatchCount, matchCount);
    }
}
