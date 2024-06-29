package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.text.DecimalFormat;
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
            Arguments.arguments(List.of(3, 3, 4, 5, 6, 1),
                                Map.of(Rank.LAST_PLACE, 1,
                                       Rank.FOURTH_PLACE, 2,
                                       Rank.THIRD_PLACE, 1,
                                       Rank.SECOND_PLACE, 1,
                                       Rank.FIRST_PLACE, 1)
            ));
    }

    @ParameterizedTest(name = "{0}의 점수가 주어지면 {1}의 rankCountMap이 만들어진다.")
    @MethodSource("methodSourceOfCreateRankCountMap")
    @DisplayName("Score들의 목록이 주어지면, 그 개수를 센 rankCountMap을 만들 수 있다.")
    void createRankCountMapTest(List<Integer> inputScores, Map<Rank, Integer> expectedRankCountMap) {
        // given
        List<Score> scores = new ArrayList<>();
        for (Integer inputScore : inputScores) {
            scores.add(new Score(inputScore));
        }
        final LottoResult lottoResult = new LottoResult(scores);
        // when
        final Map<Rank, Integer> rankCountMap = lottoResult.getRankCountMap();
        // then
        assertThat(rankCountMap)
            .isEqualTo(expectedRankCountMap);
    }

    private static Stream<Arguments> methodSourceOfCreateRankCountMapKey() {
        return Stream.of(
            Arguments.arguments(List.of(3, 3, 4, 5, 6, 1),
                                Map.of(Rank.LAST_PLACE, 1,
                                       Rank.FOURTH_PLACE, 2,
                                       Rank.THIRD_PLACE, 1,
                                       Rank.SECOND_PLACE, 1,
                                       Rank.FIRST_PLACE, 1)
            ));
    }

    @ParameterizedTest
    @MethodSource("methodSourceOfCreateRankCountMapKey")
    @DisplayName("rankCountMap의 key(Rank)들은 scoreCutoff 순서대로 정렬되어있다.")
    void rankKeyTest(List<Integer> inputScores) {
        // given
        List<Score> scores = new ArrayList<>();
        for (Integer inputScore : inputScores) {
            scores.add(new Score(inputScore));
        }
        final LottoResult lottoResult = new LottoResult(scores);
        // when
        final Map<Rank, Integer> rankCountMap = lottoResult.getRankCountMap();
        List<Rank> orderOfRank = new ArrayList<>();
        for (Rank rank : rankCountMap.keySet()) {
            orderOfRank.add(rank);
        }
        // then
        assertThat(orderOfRank)
            .isEqualTo(List.of(Rank.LAST_PLACE,
                               Rank.FOURTH_PLACE,
                               Rank.THIRD_PLACE,
                               Rank.SECOND_PLACE,
                               Rank.FIRST_PLACE));
    }


    private static Stream<Arguments> methodSourceOfGetRoi() {
        return Stream.of(
            Arguments.arguments(List.of(3, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1), 14000,
                                (double) Rank.FOURTH_PLACE.getPrizeMoney()/14000
            ));
    }

    @ParameterizedTest(name = "각 로또의 일치하는 숫자 개수가 {0}개 이고 총 구매 금액이 {1}이면 수익률은 {2}이다.")
    @MethodSource("methodSourceOfGetRoi")
    @DisplayName("총 수익률을 구할 수 있다.")
    void getRoiTest(List<Integer> inputScores, int inputPurchasePrice, double expectedROI) {
        // given
        List<Score> scores = new ArrayList<>();
        for (Integer inputScore : inputScores) {
            scores.add(new Score(inputScore));
        }
        final LottoResult lottoResult = new LottoResult(scores);
        final PurchasePrice purchasePrice = new PurchasePrice(inputPurchasePrice);
        // when
        final double roi = lottoResult.getROI(purchasePrice);
        // then
        assertThat(roi)
            .isEqualTo(expectedROI);
    }
}
