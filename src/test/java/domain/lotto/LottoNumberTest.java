package domain.lotto;

import domain.common.ExceptionMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @MethodSource(value = "numberRange")
    void 범위_내에서_객체생성_확인(int number) {
        final LottoNumber lottoNumber = new LottoNumber(number);

        Assertions.assertThat(lottoNumber.getNumber()).isEqualTo(number);
    }

    private static Stream<Arguments> numberRange() {
        List<Arguments> argumentsList = new ArrayList<>();
        for (int i=1; i<=45; i++) {
            Arguments arguments = Arguments.of(i);
            argumentsList.add(arguments);
        }
        return argumentsList.stream();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, 100})
    void 범위_외에는_예외발생(int number) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.LOTTO_NUMBER_OUT_OF_RANGE);
    }
}
