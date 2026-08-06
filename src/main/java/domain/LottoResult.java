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

    private int checkingWinningNumbers(List<LottoNumber> lottoList, List<Integer> wins, int bonusBall) {
        int count = 0;
        for (int win : wins) {
            count = resultCounting(lottoList, win, count);
        }

        if (count == 5 && containsWinningNumber(lottoList, bonusBall)) return 7;
        return count;
    }

    public List<Integer> calculateCounts(List<List<LottoNumber>> lottos, List<Integer> wins, int bonusBall) {
        List<Integer> counts = new ArrayList<>();

        for (List<LottoNumber> lotto : lottos) {
            counts.add(checkingWinningNumbers(lotto, wins, bonusBall));
        }
        return counts;
    }
}
