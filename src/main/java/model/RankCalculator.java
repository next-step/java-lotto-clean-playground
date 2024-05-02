package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankCalculator {

    private final OneLotto ans;

    public RankCalculator(OneLotto ans) {
        this.ans = ans;
    }

    private void eachLottoRank(OneLotto oneLotto) {
        List<Integer> numbers = oneLotto.getLottoNumbers();
        List<Integer> ansNumbers = ans.getLottoNumbers();
        long count = numbers.stream()
                .filter(ansNumbers::contains)
                .count();
        oneLotto.setCorrectCount((int) count);
    }

    public void allLottoRank(Lotto lotto) {
        List<OneLotto> oneLottoList = lotto.getMyLottos();
        for (OneLotto oneLotto : oneLottoList) {
            eachLottoRank(oneLotto);
        }
    }

    private long calculateTotal(List<Integer> rankList) {
        List<Integer> money = new ArrayList<>
                (Arrays.asList(0, 0, 5000, 50000, 1500000, 2000000000));
        long total = 0;
        for (int i = 2; i < rankList.size(); i++) {
            total += ((long) rankList.get(i) * money.get(i));
        }
        return total;
    }

    public double rateOfReturn(Lotto lotto) {
        List<Integer> rankList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
        for (OneLotto oneLotto : lotto.getMyLottos()) {
            int idx = oneLotto.getCorrectCount() - 1;
            rankList.set(idx, rankList.get(idx) + 1);
        }

        int balance = lotto.getBalance();
        long total = calculateTotal(rankList);

        return (double) (total / balance * 100);
    }
}
