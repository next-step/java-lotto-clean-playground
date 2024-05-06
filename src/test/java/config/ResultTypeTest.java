package config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTypeTest {

    @ParameterizedTest
    @MethodSource("methodSourceResultTypeTestArguments")
    @DisplayName("일치 개수와 보너스 유무에 맞는 결과 타입을 반환해야 한다.")
    void getResultTypeTest(int matchCount, boolean hasBonus) {
        final ResultType resultType = ResultType.getResultType(matchCount, hasBonus);
        assertThat(resultType.getMatchCount()).isEqualTo(matchCount);
        assertThat(resultType.isHasBonus()).isEqualTo(hasBonus);
    }

    private static Stream<Arguments> methodSourceResultTypeTestArguments() {
        return Stream.of(
                Arguments.arguments(6, false),
                Arguments.arguments(5, true),
                Arguments.arguments(5, false),
                Arguments.arguments(4, false),
                Arguments.arguments(3, false),
                Arguments.arguments(0, false)
        );
    }

}
