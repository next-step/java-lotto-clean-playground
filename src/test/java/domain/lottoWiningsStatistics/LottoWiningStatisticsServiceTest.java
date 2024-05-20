package domain.lottoWiningsStatistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.Rank;
import domain.lotto.Lotto;
import domain.lottoNumber.LottoNumber;
import domain.lottoTicket.LottoTicket;
import domain.lottoWinningStatistics.LottoWinningStatisticsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWiningStatisticsServiceTest {
    private final LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();
    @Test
    @DisplayName("로또 당첨 번호와 로또 번호 매칭 횟수 반환 테스트")
    public void matchLottoWinningNumberTest() {
        //given
        LottoTicket lottoTicket = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 6)));
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        //when
        int expectedMatchingCount = 6;

        int matchingCount = lottoWinningStatisticsService.matchLottoWinningNumber(lottoTicket, winningNumbers);
        //then
        assertThat(matchingCount).isEqualTo(expectedMatchingCount);

    }

    @Test
    @DisplayName("랭크 리스트 생산 테스트")
    public void generateWinningStatisticTest() {
        //given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 7;

        LottoTicket lottoTicket1 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 6)));
        LottoTicket lottoTicket2 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 7)));
        LottoTicket lottoTicket3 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 7, 8)));

        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);
        lottoTickets.add(lottoTicket3);

        //when
        Map<Rank, Integer> rankStatistic = lottoWinningStatisticsService.generateRankStatistic(lottoTickets, winningNumbers);
        Map<Rank, Integer> expectedLastRankStatistic = lottoWinningStatisticsService.findSecondWinning(rankStatistic, lottoTickets, winningNumbers, bonusBall);

        Map<Rank, Integer> lastRankSatistic = lottoWinningStatisticsService.generateWinningStatistic(lottoTickets, winningNumbers, bonusBall);

        //then
        assertAll(
                () -> assertThat(expectedLastRankStatistic.get(Rank.FIFTH_PRIZE)).isEqualTo(lastRankSatistic.get(Rank.FIFTH_PRIZE)),
                () -> assertThat(expectedLastRankStatistic.get(Rank.FOURTH_PRIZE)).isEqualTo(lastRankSatistic.get(Rank.FOURTH_PRIZE)),
                () -> assertThat(expectedLastRankStatistic.get(Rank.THIRD_PRIZE)).isEqualTo(lastRankSatistic.get(Rank.THIRD_PRIZE)),
                () -> assertThat(expectedLastRankStatistic.get(Rank.SECOND_PRIZE)).isEqualTo(lastRankSatistic.get(Rank.SECOND_PRIZE)),
                () -> assertThat(expectedLastRankStatistic.get(Rank.FIRST_PRIZE)).isEqualTo(lastRankSatistic.get(Rank.FIRST_PRIZE))
        );
    }

    @Test
    @DisplayName("순위 통계 생성 테스트")
    public void generateRankStatisticTest() {
        //given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        LottoTicket lottoTicket1 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 6)));
        LottoTicket lottoTicket2 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 7)));
        LottoTicket lottoTicket3 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 7, 8)));

        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);
        lottoTickets.add(lottoTicket3);

        Map<Rank, Integer> expectedRankStatistic = lottoWinningStatisticsService.initRankStatistic();

        lottoTickets.forEach(lottoTicket -> {
            Rank rank = Rank.of(lottoWinningStatisticsService.matchLottoWinningNumber(lottoTicket, winningNumbers), lottoWinningStatisticsService.hasBonusBall(winningNumbers));

            if(rank != null)
                expectedRankStatistic.put(rank, expectedRankStatistic.get(rank) + 1);
        });

        //when
        Map<Rank, Integer> rankStatistic = lottoWinningStatisticsService.generateRankStatistic(lottoTickets, winningNumbers);

        //then
        assertAll(
                () -> assertThat(expectedRankStatistic.get(Rank.FIFTH_PRIZE)).isEqualTo(rankStatistic.get(Rank.FIFTH_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.FOURTH_PRIZE)).isEqualTo(rankStatistic.get(Rank.FOURTH_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.THIRD_PRIZE)).isEqualTo(rankStatistic.get(Rank.THIRD_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.SECOND_PRIZE)).isEqualTo(rankStatistic.get(Rank.SECOND_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.FIRST_PRIZE)).isEqualTo(rankStatistic.get(Rank.FIRST_PRIZE))
        );
    }

    @Test
    @DisplayName("순위 통계 초기화 테스트")
    public void initRankStatisticTest() {
        //given
        Map<Rank, Integer> expectedRankStatistic = new HashMap<>();

        for(Rank rank : Rank.values()) {
            expectedRankStatistic.put(rank, 0);
        }

        //when
        Map<Rank, Integer> rankStatistic = lottoWinningStatisticsService.initRankStatistic();

        //then
        assertAll(
                () -> assertThat(expectedRankStatistic.get(Rank.FIFTH_PRIZE)).isEqualTo(rankStatistic.get(Rank.FIFTH_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.FOURTH_PRIZE)).isEqualTo(rankStatistic.get(Rank.FOURTH_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.THIRD_PRIZE)).isEqualTo(rankStatistic.get(Rank.THIRD_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.SECOND_PRIZE)).isEqualTo(rankStatistic.get(Rank.SECOND_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.FIRST_PRIZE)).isEqualTo(rankStatistic.get(Rank.FIRST_PRIZE))
        );
    }

    @Test
    @DisplayName("2등 당첨자 찾는 메서드 테스트")
    public void findSecondwinningTest() {
        //given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoTicket lottoTicket1 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 6)));
        LottoTicket lottoTicket2 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 7)));
        LottoTicket lottoTicket3 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 7, 8)));
        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);
        lottoTickets.add(lottoTicket3);

        Map<Rank, Integer> expectedRankStatistic = lottoWinningStatisticsService.generateRankStatistic(lottoTickets, winningNumbers);

        //when
        Map<Rank, Integer> rankStatistic = lottoWinningStatisticsService.findSecondWinning(expectedRankStatistic, lottoTickets, winningNumbers, bonusNumber);

        List<Integer> bonusWinningNumbers = new ArrayList<>(winningNumbers);
        bonusWinningNumbers.add(bonusNumber);

        lottoTickets.forEach(lottoTicket -> {
            Rank rank = Rank.of(lottoWinningStatisticsService.matchLottoWinningNumber(lottoTicket, bonusWinningNumbers), lottoWinningStatisticsService.hasBonusBall(bonusWinningNumbers));
            if(rank != null)
                expectedRankStatistic.put(rank, expectedRankStatistic.get(rank) + 1);
        });


        //then
        assertAll(
                () -> assertThat(expectedRankStatistic.get(Rank.FIFTH_PRIZE)).isEqualTo(rankStatistic.get(Rank.FIFTH_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.FOURTH_PRIZE)).isEqualTo(rankStatistic.get(Rank.FOURTH_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.THIRD_PRIZE)).isEqualTo(rankStatistic.get(Rank.THIRD_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.SECOND_PRIZE)).isEqualTo(rankStatistic.get(Rank.SECOND_PRIZE)),
                () -> assertThat(expectedRankStatistic.get(Rank.FIRST_PRIZE)).isEqualTo(rankStatistic.get(Rank.FIRST_PRIZE))
        );
    }

    @Test
    @DisplayName("보너스볼 포함한 당첨 번호인지 확인 테스트")
    public void hasBonusBallTest() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6, 7);
        boolean expected = false;
        //when
        boolean real = lottoWinningStatisticsService.hasBonusBall(winningNumber);
        //then
        assertThat(expected).isEqualTo(real);
    }

    @Test
    @DisplayName("로또 구매 당첨금 계산 테스트")
    public void caculateWinningTest() {
        //given
        Map<Rank, Integer> rankStatistic = lottoWinningStatisticsService.initRankStatistic();
        rankStatistic.put(Rank.FIFTH_PRIZE, rankStatistic.get(Rank.FIFTH_PRIZE) + 1);
        int winnings = lottoWinningStatisticsService.caculateWinnig(rankStatistic);

        Lotto lotto = new Lotto("3000");
        double expectedReturnOfInvestment = (double) winnings / lotto.getLottoMoney();

        //when
        double returnOfInvestment = lottoWinningStatisticsService.caculateReturnOfInvestment(lotto, rankStatistic);

        //then
        assertThat(expectedReturnOfInvestment).isEqualTo(returnOfInvestment);
    }

    @Test
    @DisplayName("로또 구매로 얻은 수익률 반환 테스트")
    public void caculateReturnOfInvestmentTest() {
        //given
        Map<Rank, Integer> rankStatistic = lottoWinningStatisticsService.initRankStatistic();

        int expectedWinnings = 0;
        for(Rank rank : Rank.values()) {
            expectedWinnings += rankStatistic.get(rank) * rank.getRankMoney();
        }

        //when
        int realWinnings = lottoWinningStatisticsService.caculateWinnig(rankStatistic);

        //then
        assertThat(expectedWinnings).isEqualTo(realWinnings);
    }
}