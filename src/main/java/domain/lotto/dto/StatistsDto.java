package domain.lotto.dto;

import domain.common.Money;
import domain.lotto.LottoResult;
import domain.lotto.LottoTicket;
import domain.lotto.Prize;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StatistsDto {

    private final List<MatchResult> matchResults;
    
    private final double profitRate;

    public StatistsDto(List<MatchResult> matchResults, double profitRate) {
        this.matchResults = matchResults;
        this.profitRate = profitRate;
    }

    public List<MatchResult> getMatchResults() {
        return matchResults;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public static StatistsDto makeStatistics(Money myMoney, List<LottoTicket> lottoTickets, LottoResult lottoResult) {
        Map<Prize, Integer> prizes = initPrizes();

        lottoTickets.forEach(lottoTicket -> {
            Prize prize = lottoResult.findPrize(lottoTicket);
            prizes.put(prize, prizes.getOrDefault(prize, 0) + 1);
        });

        List<MatchResult> matchResults = getMathResult(prizes);
        Money total = calculateTotalMoney(prizes);
        final double profitRate = myMoney.getProfitRate(total);
        return new StatistsDto(matchResults, profitRate);
    }

    private static Map<Prize, Integer> initPrizes() {
        Map<Prize, Integer> prizes = new LinkedHashMap<>();
        prizes.put(Prize.FOURTH, 0);
        prizes.put(Prize.THIRD, 0);
        prizes.put(Prize.SECOND, 0);
        prizes.put(Prize.SECOND_BONUS_BALL, 0);
        prizes.put(Prize.FIRST, 0);
        return prizes;
    }

    private static List<MatchResult> getMathResult(Map<Prize, Integer> prizes) {
        return prizes.keySet().stream()
            .filter(k -> k != Prize.LOSING_TICKET)
            .map(k -> new MatchResult(k.getMatch(), k.getMoney().getAmount(), prizes.get(k), k.hasBonusNumber()))
            .toList();
    }

    private static Money calculateTotalMoney(Map<Prize, Integer> prizes) {
        int totalPrize = prizes.keySet().stream()
            .mapToInt(k -> k.getMoney().getAmount() * prizes.get(k))
            .sum();
        return new Money(totalPrize);
    }
}
