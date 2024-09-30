package domain;

import java.util.HashMap;
import java.util.List;

public class WinnerStatistic {
    private final LottoNumbers winnerNumbers;
    private final LottoNumber bonusNumber;

    private final HashMap<Match, Integer> match;

    public WinnerStatistic(LottoNumbers winnerNumbers, LottoNumber bonusNumber, List<Lotto> lottos) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
        this.match = findMatchedNumber(lottos);
    }

    private HashMap<Match, Integer> findMatchedNumber(List<Lotto> lottos) {
        HashMap<Match, Integer> matchCounts = initializeMatchMap();
        int cnt = 0;
        boolean bonusFlag = false;

        for (Lotto lotto : lottos) {
            cnt = 0;
            bonusFlag = false;

            List<LottoNumber> lottoNumbers = lotto.getLottoNumbers().getNumbers();

            for (int j = 0; j < winnerNumbers.getNumbers().size(); j++) {
                if (lottoNumbers.contains(winnerNumbers.getNumbers().get(j))) {
                    cnt++;
                }
            }

            if (cnt < 6 && lottoNumbers.contains(bonusNumber)) {
                bonusFlag = true;
            }

            Match match = Match.from(cnt, bonusFlag);

            if (match != null) {
                matchCounts.put(match, matchCounts.get(match) + 1);
            }
        }


        return matchCounts;
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
