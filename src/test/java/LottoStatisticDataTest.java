import domain.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class LottoStatisticDataTest {
    @ParameterizedTest
    @MethodSource("methodSourceTestArguments")
    void 수익률을_계산한다(LottoNumbers winnerNumbers, LottoNumber bonusNumber, List<Lotto> puchasedLottos) {
        LottoStatisticData lottoStatisticData = new LottoStatisticData(winnerNumbers, bonusNumber, puchasedLottos);

        var actual = lottoStatisticData.calculateRate(puchasedLottos.size());
        var expect = 253944.37;

        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("methodSourceTestArguments")
    void 당첨_통계를_생성한다(LottoNumbers winnerNumbers, LottoNumber bonusNumber, List<Lotto> puchasedLottos) {
        LottoStatisticData lottoStatisticData = new LottoStatisticData(winnerNumbers, bonusNumber, puchasedLottos);

        System.out.println(lottoStatisticData.calculateRate(8000));
//        var actual = 1;
        var expect = new HashMap<Match, Integer>() {{
            put(Match.THREE, 1);
            put(Match.FOUR, 1);
            put(Match.FIVE, 1);
            put(Match.FIVEWITHBONUS, 1);
            put(Match.SIX, 1);
        }};

        assertAll(() -> {
            assertThat(lottoStatisticData.getMatchStatistic().get(Match.THREE)).isEqualTo(expect.get(Match.THREE));
            assertThat(lottoStatisticData.getMatchStatistic().get(Match.FOUR)).isEqualTo(expect.get(Match.FOUR));
            assertThat(lottoStatisticData.getMatchStatistic().get(Match.FIVE)).isEqualTo(expect.get(Match.FIVE));
            assertThat(lottoStatisticData.getMatchStatistic().get(Match.FIVEWITHBONUS)).isEqualTo(expect.get(Match.FIVEWITHBONUS));
            assertThat(lottoStatisticData.getMatchStatistic().get(Match.SIX)).isEqualTo(expect.get(Match.SIX));
        });



    }
    private static Stream<Arguments> methodSourceTestArguments() {
        LottoNumbers winnerNumbers = new LottoNumbers(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        List<Lotto> puchasedLottos = List.of(
                new Lotto(
                        new LottoNumbers(
                                List.of(
                                        new LottoNumber(1),
                                        new LottoNumber(2),
                                        new LottoNumber(3),
                                        new LottoNumber(4),
                                        new LottoNumber(5),
                                        new LottoNumber(6)
                                )
                        )
                ),
                new Lotto(
                        new LottoNumbers(
                                List.of(
                                        new LottoNumber(1),
                                        new LottoNumber(2),
                                        new LottoNumber(3),
                                        new LottoNumber(4),
                                        new LottoNumber(5),
                                        new LottoNumber(7)
                                )
                        )
                ),
                new Lotto(
                        new LottoNumbers(
                                List.of(
                                        new LottoNumber(1),
                                        new LottoNumber(2),
                                        new LottoNumber(3),
                                        new LottoNumber(4),
                                        new LottoNumber(5),
                                        new LottoNumber(45)
                                )
                        )
                ),
                new Lotto(
                        new LottoNumbers(
                                List.of(
                                        new LottoNumber(1),
                                        new LottoNumber(2),
                                        new LottoNumber(3),
                                        new LottoNumber(4),
                                        new LottoNumber(44),
                                        new LottoNumber(45)
                                )
                        )
                ),
                new Lotto(
                        new LottoNumbers(
                                List.of(
                                        new LottoNumber(1),
                                        new LottoNumber(2),
                                        new LottoNumber(3),
                                        new LottoNumber(43),
                                        new LottoNumber(44),
                                        new LottoNumber(45)
                                )
                        )
                ),
                new Lotto(
                        new LottoNumbers(
                                List.of(
                                        new LottoNumber(1),
                                        new LottoNumber(2),
                                        new LottoNumber(42),
                                        new LottoNumber(43),
                                        new LottoNumber(44),
                                        new LottoNumber(45)
                                )
                        )
                ),
                new Lotto(
                        new LottoNumbers(
                                List.of(
                                        new LottoNumber(1),
                                        new LottoNumber(41),
                                        new LottoNumber(42),
                                        new LottoNumber(43),
                                        new LottoNumber(44),
                                        new LottoNumber(45)
                                )
                        )
                ),
                new Lotto(
                        new LottoNumbers(
                                List.of(
                                        new LottoNumber(40),
                                        new LottoNumber(41),
                                        new LottoNumber(42),
                                        new LottoNumber(43),
                                        new LottoNumber(44),
                                        new LottoNumber(45)
                                )
                        )
                )
        );

        LottoNumber bonusNumber = new LottoNumber(7);
        return Stream.of(
                Arguments.of(winnerNumbers, bonusNumber, puchasedLottos)
        );
    }
}
