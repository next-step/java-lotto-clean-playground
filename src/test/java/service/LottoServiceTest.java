package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.Lottos;
import domain.NumberGenerator;
import domain.RandomLottoNumberGenerator;
import java.io.ByteArrayInputStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    private void setSetIn(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    private static Stream<Arguments> methodSourceOfCreateLottoTest() {
        return Stream.of(
            Arguments.arguments("14000", 14),
            Arguments.arguments("0", 0),
            Arguments.arguments("-1000", 0),
            Arguments.arguments("1500", 1),
            Arguments.arguments("23245", 23)
        );
    }

    @ParameterizedTest(name = "구입금액 {0}이면 {1}개의 로또가 만들어진다.")
    @MethodSource("methodSourceOfCreateLottoTest")
    @DisplayName("구입금액으로 1000원짜리 로또를 몇 장 살 수 있는지 계산할 수 있다.")
    void createLottoTest(String inputPrice, int expectedNumberOfLotto) {
        // given
        NumberGenerator numberGenerator = new RandomLottoNumberGenerator();
        setSetIn(inputPrice);
        // when
        Lottos lottos = lottoService.getLottos(numberGenerator);
        // then
        assertThat(lottos.getSize())
            .isEqualTo(expectedNumberOfLotto);

    }
}
