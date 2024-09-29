import domain.LottoMakeStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

@DisplayName("로또 생성 전략 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RandomLottoMakeStrategyTest {

    @DisplayName("6개의 번호를 갖는 로또를 만들 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "5, true",
            "14, true",
            "16, true",
            "17, false",
            "35, true",
            "37, false",
            "38, true",
            "44, true",
    })
    public void makeLotto(final int lottoNumber, final boolean expected) {
        //given
        LottoMakeStrategy lottoMakeStrategy = new TestLottoMakeStrategy();

        //when
        List<Integer> result = lottoMakeStrategy.makeLotto();

        //then
        Assertions.assertThat(result.contains(lottoNumber)).isEqualTo(expected);
    }

}
