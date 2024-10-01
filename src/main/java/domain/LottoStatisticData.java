package domain;

import java.util.HashMap;
import java.util.List;

public class LottoStatisticData {
    private final LottoNumbers winnerNumbers;
    private final LottoNumber bonusNumber;
    private final HashMap<Match, Integer> matchStatistic;

    public LottoStatisticData(LottoNumbers winnerNumbers, LottoNumber bonusNumber, List<Lotto> lottos) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
        this.matchStatistic = compileLottosStatistic(lottos);
    }

    private HashMap<Match, Integer> compileLottosStatistic(List<Lotto> lottos) {
        HashMap<Match, Integer> matchCounts = initializeMatchMap();

        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = lotto.getLottoNumbers().getNumbers();

            int cnt = findMatchNumberCount(lottoNumbers);

            boolean bonusFlag = isBonusMatched(cnt, lottoNumbers);

            upDateMatchStatistic(cnt, bonusFlag, matchCounts);
        }

        return matchCounts;
    }

    private int findMatchNumberCount(List<LottoNumber> lottoNumbers) {
        int cnt = 0;

        for (LottoNumber lottoNumber : winnerNumbers.getNumbers()) {
            if (lottoNumbers.contains(lottoNumber)) {
                cnt++;
            }
        }

        return cnt;
    }

    private boolean isBonusMatched(int cnt, List<LottoNumber> lottoNumbers) {
        return cnt == 5 && lottoNumbers.contains(bonusNumber);
    }

    private void upDateMatchStatistic(int cnt, boolean bonusFlag, HashMap<Match, Integer> matchCounts) {
        Match match = Match.from(cnt, bonusFlag);

        if (match != null) {
            matchCounts.put(match, matchCounts.get(match) + 1);
        }
    }



    private HashMap<Match, Integer> initializeMatchMap() {
        HashMap<Match, Integer> matchCounts = new HashMap<Match, Integer>();
        for (Match m: Match.values()) {
            matchCounts.put(m, 0);
        }

        return matchCounts;
    }

    public double calculateRate(int amount) {
        int totalEarnAmount = 0;
        for (Match m: matchStatistic.keySet()) {
            totalEarnAmount += matchStatistic.get(m) * m.getPrice();
        }

        return (double) totalEarnAmount / amount;
    }

    public HashMap<Match, Integer> getMatchStatistic() {
        return matchStatistic;
    }
}
