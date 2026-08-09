package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {

    private boolean containsWinningNumber(List<LottoNumber> lottoList, int win) {
        return lottoList.contains(LottoNumber.from(win));
    }

    private int resultCounting(List<LottoNumber> lottoList, int win, int count) {
        if (containsWinningNumber(lottoList, win)) {
            count++;
        }
        return count;
    }

    private Rank checkingWinningNumbers(List<LottoNumber> lottoList, List<Integer> wins, int bonusBall) {
        int count = 0;
        for (int win : wins) {
            count = resultCounting(lottoList, win, count);
        }

        boolean matchBonus = containsWinningNumber(lottoList, bonusBall);
        return Rank.find(count, matchBonus);
    }

    public List<Rank> calculateRanks(List<List<LottoNumber>> lottos, List<Integer> wins, int bonusBall) {
        List<Rank> ranks = new ArrayList<>();

        for (List<LottoNumber> lotto : lottos) {
            ranks.add(checkingWinningNumbers(lotto, wins, bonusBall));
        }
        return ranks;
    }
}
