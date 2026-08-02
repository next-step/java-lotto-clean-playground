package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {

    private boolean containsWinningNumber(List<Integer> lottoList, int win) {
        return lottoList.contains(win);
    }

    private int resultCounting(List<Integer> lottoList, int win, int count) {
        if (containsWinningNumber(lottoList, win)) {
            count++;
        }
        return count;
    }

    private int checkingWinningNumbers(List<Integer> lottoList, List<Integer> wins) {
        int count = 0;
        for (int win : wins) {
            count = resultCounting(lottoList, win, count);
        }
        return count;
    }

    public List<Integer> calculateCounts(List<List<Integer>> lottos, List<Integer> wins) {
        List<Integer> counts = new ArrayList<>();

        for (List<Integer> lotto : lottos) {
            counts.add(checkingWinningNumbers(lotto, wins));
        }
        return counts;
    }
}
