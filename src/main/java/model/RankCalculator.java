package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankCalculator {

    private final OneLotto ans;
    List<Integer> correctNum = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

    public RankCalculator(OneLotto ans) {
        this.ans = ans;
    }

    private void eachLottoRank(OneLotto oneLotto) {
        List<Integer> numbers = oneLotto.getLottoNumbers(); //로또 한장
        List<Integer> ansNumbers = ans.getLottoNumbers(); //정답 로또 번호
        long count = numbers.stream()
                .filter(ansNumbers::contains)
                .count(); //몇개맞지?
        correctNum.set((int) (count - 1), correctNum.get((int) count - 1) + 1);
    }

    public void allLottoRank(Lottos lottos) {
        List<OneLotto> oneLottoList = lottos.getMyLottos();
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

    public double rateOfReturn(Lottos lottos) {
        int balance = lottos.getBalance();
        long total = calculateTotal(correctNum);

        return (double) (total / balance * 100);
    }
}
