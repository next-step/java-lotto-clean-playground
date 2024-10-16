package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatisticData {
    private final List<LottoNumber> winnerNumbers;
    private final LottoNumber bonusNumber;
    private final Map<Match, Integer> matchStatistic;

    public LottoStatisticData(List<LottoNumber> winnerNumbers, LottoNumber bonusNumber, List<Lotto> purchasedLottos) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
        this.matchStatistic = calculateLottosStatistic(purchasedLottos);
    }

    private Map<Match, Integer> calculateLottosStatistic(List<Lotto> lottos) {
        Map<Match, Integer> matchCounts = initializeMatchMap();

        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = lotto.getLottoNumbers();

            int cnt = findMatchNumberCount(lottoNumbers);

            boolean bonusFlag = isBonusMatched(cnt, lottoNumbers);

            updateMatchStatistic(cnt, bonusFlag, matchCounts);
        }

        return matchCounts;
    }

    private int findMatchNumberCount(List<LottoNumber> lottoNumbers) {
        int cnt = 0;

        for (LottoNumber lottoNumber : winnerNumbers) {
            if (lottoNumbers.contains(lottoNumber)) {
                cnt++;
            }
        }

        return cnt;
    }

    private boolean isBonusMatched(int cnt, List<LottoNumber> lottoNumbers) {
        return cnt == 5 && lottoNumbers.contains(bonusNumber);
    }

    private void updateMatchStatistic(int cnt, boolean bonusFlag, Map<Match, Integer> matchCounts) {
        Match match = Match.from(cnt, bonusFlag);

        if (match != null) {
            matchCounts.put(match, matchCounts.get(match) + 1);
        }
    }

    private Map<Match, Integer> initializeMatchMap() {
        return Arrays.stream(Match.values())
                .collect(Collectors.toMap(v -> v, v -> 0));
    }

    public double calculateRate(int amount) {
        int totalEarnAmount = 0;

        for (Match m: matchStatistic.keySet()) {
            totalEarnAmount += matchStatistic.get(m) * m.getPrice();
        }

        return Math.floor((double) totalEarnAmount / amount * 100)/100.0;
    }

    public Map<Match, Integer> getMatchStatistic() {
        return matchStatistic;
    }
}
