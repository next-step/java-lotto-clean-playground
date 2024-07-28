package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultTest {

    private static Stream<Arguments> methodSourceOfCreateRankCountMap() {
        return Stream.of(
            Arguments.arguments(List.of(3, 3, 4, 5, 6, 1, 5),
                                List.of(true, true, false, false, true, false, true),
                                Map.of(Rank.LAST_PLACE, 1,
                                       Rank.FIFTH_PLACE, 2,
                                       Rank.FOURTH_PLACE, 1,
                                       Rank.THIRD_PLACE, 1,
                                       Rank.SECOND_PLACE, 1,
                                       Rank.FIRST_PLACE, 1)
            ));
    }

    @ParameterizedTest(name = "{0}의 점수와 {1}의 보너스번호 일치 여부가 주어지면 {1}의 rankCountMap이 만들어진다.")
    @MethodSource("methodSourceOfCreateRankCountMap")
    @DisplayName("Score들의 목록이 주어지면, 그 개수를 센 rankCountMap을 만들 수 있다.")
    void createRankCountMapTest(List<Integer> inputScores, List<Boolean> isMatchingLst,
                                Map<Rank, Integer> expectedRankCountMap) {
        // given
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < inputScores.size(); i++) {
            scores.add(new Score(inputScores.get(i), isMatchingLst.get(i)));
        }
        final LottoResult lottoResult = new LottoResult(scores);
        // when
        final Map<Rank, Integer> rankCountMap = lottoResult.getRankCountMap();
        // then
        assertThat(rankCountMap)
            .isEqualTo(expectedRankCountMap);
    }


    private static Stream<Arguments> methodSourceOfGetRoi() {
        return Stream.of(
            Arguments.arguments(List.of(3, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1),
                                List.of(false, false, false, false, false, false, false, false,
                                        false, false, false, false, false, false, false),
                                14000,
                                (double) Rank.FIFTH_PLACE.getPrizeMoney() / 14000
            ));
    }

    @ParameterizedTest(name = "각 로또의 일치하는 숫자 개수가 {0}개이고 보너스번호와 일치 여부는 {1}이고 총 구매 금액이 {1}이면 수익률은 {2}이다.")
    @MethodSource("methodSourceOfGetRoi")
    @DisplayName("총 수익률을 구할 수 있다.")
    void getRoiTest(List<Integer> inputScores, List<Boolean> isMatchingLst, int inputPurchasePrice,
                    double expectedROI) {
        // given
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < inputScores.size(); i++) {
            scores.add(new Score(inputScores.get(i), isMatchingLst.get(i)));
        }
        final LottoResult lottoResult = new LottoResult(scores);
        final LottoPurchasePrice lottoPurchasePrice = new LottoPurchasePrice(inputPurchasePrice);
        // when
        final double roi = lottoResult.getROI(lottoPurchasePrice);
        // then
        assertThat(roi)
            .isEqualTo(expectedROI);
    }
}
