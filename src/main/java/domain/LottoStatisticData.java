package domain;

import java.util.HashMap;
import java.util.List;

public class LottoStatisticData {
    private final LottoNumbers winnerNumbers;
    private final LottoNumber bonusNumber;

    private final HashMap<Match, Integer> match;

    public LottoStatisticData(LottoNumbers winnerNumbers, LottoNumber bonusNumber, List<Lotto> lottos) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
        this.match = compileLottosStatistic(lottos);
    }

    private HashMap<Match, Integer> compileLottosStatistic(List<Lotto> lottos) {
        HashMap<Match, Integer> matchCounts = initializeMatchMap();

        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = lotto.getLottoNumbers().getNumbers();

            int cnt = findMatchNumberCount(lottoNumbers);

            boolean bonusFlag = findBonusMatched(cnt, lottoNumbers);

            Match match = Match.from(cnt, bonusFlag);

            if (match != null) {
                matchCounts.put(match, matchCounts.get(match) + 1);
            }
        }

        return matchCounts;
    }

    private int findMatchNumberCount(List<LottoNumber> lottoNumbers) {
        int cnt = 0;

        for (int j = 0; j < winnerNumbers.getNumbers().size(); j++) {
            if (lottoNumbers.contains(winnerNumbers.getNumbers().get(j))) {
                cnt++;
            }
        }

        return cnt;
    }

    private boolean findBonusMatched(int cnt, List<LottoNumber> lottoNumbers) {
        return cnt < 6 && lottoNumbers.contains(bonusNumber);
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
        for (Match m: match.keySet()) {
            totalEarnAmount += match.get(m) * m.getPrice();
        }

        return (double) totalEarnAmount / amount;
    }

    public HashMap<Match, Integer> getMatch() {
        return match;
    }
}
