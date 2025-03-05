package model;

import fixture.LottoResultFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private static final int TEST_REPEAT_COUNT = 100;
    private static final Comparator<LottoResult> NATURAL_COMPARATOR = null;

    @Test
    @DisplayName("increaseLottoAmount() 호출시마다 lottoAmount를 1 증가시킨다")
    void increaseLottoAmount() {
        LottoResult lottoResult = LottoResultFixture.THREE_EQUALS_RESULT.getValue();

        for (int expectedLottoAmount = 0; expectedLottoAmount < TEST_REPEAT_COUNT; expectedLottoAmount++) {
            int actualLottoAmount = lottoResult.getLottoAmount();
            assertThat(actualLottoAmount).isEqualTo(expectedLottoAmount);

            lottoResult.increaseLottoAmount();
        }
    }

    @Test
    @DisplayName("내부적으로 보유한 LottoRank를 기준으로 정렬된다")
    void sortedByLottoRank() {
        List<LottoResult> lottoResultCollection = new ArrayList<>(LottoResultFixture.getEveryInstance());
        lottoResultCollection.sort(NATURAL_COMPARATOR);
        List<LottoRank> sortedLottoRanks = getSortedLottoRanks();

        for (int i = 0; i < lottoResultCollection.size(); i++) {
            LottoResult lottoResult = lottoResultCollection.get(i);
            LottoRank lottoRank = sortedLottoRanks.get(i);

            assertThat(lottoResult.getPrizeAmount()).isEqualTo(lottoRank.prizeAmount);
        }
    }

    @Test
    @DisplayName("해당 로또 결과에 대한 equalCount를 반환한다")
    void getEqualsCountAboutLottoResult() {
        LottoResultFixture[] lottoResultFixtures = LottoResultFixture.values();

        for (LottoResultFixture lottoResultFixture : lottoResultFixtures) {
            LottoResult actualLottoResult = lottoResultFixture.getValue();
            LottoRank expectedLottoRank = lottoResultFixture.getLottoRank();

            int actualEqualCount = actualLottoResult.getEqualCount();
            int expectedEqualCount = expectedLottoRank.equalCount;

            assertThat(actualEqualCount).isEqualTo(expectedEqualCount);
        }
    }

    @Test
    @DisplayName("해당 로또 결과에 대한 prizeAmount를 반환한다")
    void getPrizeAmountAboutLottoResult() {
        LottoResultFixture[] lottoResultFixtures = LottoResultFixture.values();

        for (LottoResultFixture lottoResultFixture : lottoResultFixtures) {
            LottoResult actualLottoResult = lottoResultFixture.getValue();
            LottoRank expectedLottoRank = lottoResultFixture.getLottoRank();

            int actualPrizeAmount = actualLottoResult.getPrizeAmount();
            int expectedPrizeAmount = expectedLottoRank.prizeAmount;

            assertThat(actualPrizeAmount).isEqualTo(expectedPrizeAmount);
        }
    }

    @Test
    @DisplayName("해당 로또 결과가 보너스볼 관련 결과인지를 반환한다")
    void getBonusBallResult() {
        LottoResult bonusBallResult = LottoResultFixture.FIVE_WITH_BONUS_BALL_EQUALS_RESULT.getValue();
        LottoResult notBonusBallResult = LottoResultFixture.SIX_EQUALS_RESULT.getValue();

        assertThat(bonusBallResult.isBonusBallResult()).isTrue();
        assertThat(notBonusBallResult.isBonusBallResult()).isFalse();
    }

    @Nested
    @DisplayName("해당 로또 결과의 총 상금을 반환한다")
    class getTotalPrizeAmount {

        @ParameterizedTest
        @DisplayName("3개 일치 로또의 총 상금을 반환한다")
        @ValueSource(ints = {1, 10, 100, 1000})
        void getTotalPrizeAmountOfThreeEqualsLotto(int lottoAmount) {
            LottoResult lottoResultAboutSixEquals = LottoResultFixture.THREE_EQUALS_RESULT.getValue();

            for (int i = 0; i < lottoAmount; i++) {
                lottoResultAboutSixEquals.increaseLottoAmount();
            }

            int actualTotalPrizeAmount = lottoResultAboutSixEquals.getTotalPrizeAmount();
            int expectedTotalPrizeAmount = LottoRank.THREE_EQUALS.prizeAmount * lottoAmount;

            assertThat(actualTotalPrizeAmount).isEqualTo(expectedTotalPrizeAmount);
        }

        @ParameterizedTest
        @DisplayName("4개 일치 로또의 총 상금을 반환한다")
        @ValueSource(ints = {1, 10, 100, 1000})
        void getTotalPrizeAmountOfFourEqualsLotto(int lottoAmount) {
            LottoResult lottoResultAboutSixEquals = LottoResultFixture.FOUR_EQUALS_RESULT.getValue();

            for (int i = 0; i < lottoAmount; i++) {
                lottoResultAboutSixEquals.increaseLottoAmount();
            }

            int actualTotalPrizeAmount = lottoResultAboutSixEquals.getTotalPrizeAmount();
            int expectedTotalPrizeAmount = LottoRank.FOUR_EQUALS.prizeAmount * lottoAmount;

            assertThat(actualTotalPrizeAmount).isEqualTo(expectedTotalPrizeAmount);
        }

        @ParameterizedTest
        @DisplayName("5개 일치 로또의 총 상금을 반환한다")
        @ValueSource(ints = {1, 10, 100, 1000})
        void getTotalPrizeAmountOfFiveEqualsLotto(int lottoAmount) {
            LottoResult lottoResultAboutSixEquals = LottoResultFixture.FIVE_EQUALS_RESULT.getValue();

            for (int i = 0; i < lottoAmount; i++) {
                lottoResultAboutSixEquals.increaseLottoAmount();
            }

            int actualTotalPrizeAmount = lottoResultAboutSixEquals.getTotalPrizeAmount();
            int expectedTotalPrizeAmount = LottoRank.FIVE_EQUALS.prizeAmount * lottoAmount;

            assertThat(actualTotalPrizeAmount).isEqualTo(expectedTotalPrizeAmount);
        }

        @ParameterizedTest
        @DisplayName("5개와 보너스 볼 일치 로또의 총 상금을 반환한다")
        @ValueSource(ints = {1, 10, 100, 1000})
        void getTotalPrizeAmountOfFiveEqualsWithBonusBallLotto(int lottoAmount) {
            LottoResult lottoResultAboutSixEquals = LottoResultFixture.FIVE_WITH_BONUS_BALL_EQUALS_RESULT.getValue();

            for (int i = 0; i < lottoAmount; i++) {
                lottoResultAboutSixEquals.increaseLottoAmount();
            }

            int actualTotalPrizeAmount = lottoResultAboutSixEquals.getTotalPrizeAmount();
            int expectedTotalPrizeAmount = LottoRank.FIVE_WITH_BONUS_EQUALS.prizeAmount * lottoAmount;

            assertThat(actualTotalPrizeAmount).isEqualTo(expectedTotalPrizeAmount);
        }

        @ParameterizedTest
        @DisplayName("6개 일치 로또의 총 상금을 반환한다")
        @ValueSource(ints = {1, 10, 100, 1000})
        void getTotalPrizeAmountOfSixEqualsLotto(int lottoAmount) {
            LottoResult lottoResultAboutSixEquals = LottoResultFixture.SIX_EQUALS_RESULT.getValue();

            for (int i = 0; i < lottoAmount; i++) {
                lottoResultAboutSixEquals.increaseLottoAmount();
            }

            int actualTotalPrizeAmount = lottoResultAboutSixEquals.getTotalPrizeAmount();
            int expectedTotalPrizeAmount = LottoRank.SIX_EQUALS.prizeAmount * lottoAmount;

            assertThat(actualTotalPrizeAmount).isEqualTo(expectedTotalPrizeAmount);
        }

    }

    private List<LottoRank> getSortedLottoRanks() {
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank != LottoRank.NO_PRIZE)
                .sorted()
                .toList();
    }

}
