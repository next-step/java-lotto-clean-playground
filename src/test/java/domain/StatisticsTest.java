package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters") // 한글과 같은 아스키 코드가 아닌 문자에 밑줄이 쳐지지 않음
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // UnderScore(_)가 공백으로 표시
class StatisticsTest {

    private Statistics statistics;

    @BeforeEach
    void setUp() {
        statistics = new Statistics();
    }

    @Test
    void 처음_생성된_통계는_0으로_초기화되어있다() {
        Map<Prize, Integer> matchCounts = statistics.getMatchCounts();
        for (Prize prize : Prize.values()) {
            assertEquals(0, matchCounts.get(prize), "초기 카운트는 0이어야 합니다.");
        }
    }

    @Test
    void n개_일치하는지_확인() {
        // 6개
        statistics.updateStatistics(6, false);
        assertEquals(1, statistics.getMatchCounts().get(Prize.SIX_MATCHES), "6개 일치 횟수는 1이어야 합니다.");

        // 5개 + 보너스 번호
        statistics.updateStatistics(5, true);
        assertEquals(1, statistics.getMatchCounts().get(Prize.FIVE_MATCHES_BONUS), "5개 일치 + 보너스 횟수는 1이어야 합니다.");

        // 5개 (보너스 번호 X)
        statistics.updateStatistics(5, false);
        assertEquals(1, statistics.getMatchCounts().get(Prize.FIVE_MATCHES), "5개 일치 횟수는 1이어야 합니다.");

        // 4개
        statistics.updateStatistics(4, false);
        assertEquals(1, statistics.getMatchCounts().get(Prize.FOUR_MATCHES), "4개 일치 횟수는 1이어야 합니다.");

        // 3개
        statistics.updateStatistics(3, false);
        assertEquals(1, statistics.getMatchCounts().get(Prize.THREE_MATCHES), "3개 일치 횟수는 1이어야 합니다.");
    }

    @Test
    void 수익률_계산_정확성_테스트() {
        int totalSpent = 10000;
        statistics.updateStatistics(6, false);
        statistics.updateStatistics(5, true);
        statistics.updateStatistics(5, false);
        statistics.updateStatistics(4, false);
        statistics.updateStatistics(3, false); // 상황 가정

        // 수익률 계산
        double returnRate = statistics.calculateReturnRate(totalSpent);

        // 수익률 검증
        int totalEarnings = Prize.SIX_MATCHES.getPrizeAmount()
                + Prize.FIVE_MATCHES_BONUS.getPrizeAmount()
                + Prize.FIVE_MATCHES.getPrizeAmount()
                + Prize.FOUR_MATCHES.getPrizeAmount()
                + Prize.THREE_MATCHES.getPrizeAmount();
        double expectedReturnRate = (double) totalEarnings / totalSpent;

        assertEquals(expectedReturnRate, returnRate);
    }
}
