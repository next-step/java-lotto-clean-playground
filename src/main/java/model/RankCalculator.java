package model;

import java.util.List;

public class RankCalculator {

    private final List<Integer> ans;

    public RankCalculator(List<Integer> ans) {
        this.ans = ans;
    }

    private void eachLottoRank(OneLotto oneLotto) {
        List<Integer> numbers = oneLotto.getLottoNumbers(); //로또 한장
        long count = numbers.stream()
                .filter(ans::contains)
                .count(); //몇개맞지?
        oneLotto.setCorrectNum((int) count);
    }

    public void allLottoRank(Lottos lottos) {
        List<OneLotto> oneLottoList = lottos.getMyLottos();
        for (OneLotto oneLotto : oneLottoList) {
            eachLottoRank(oneLotto);
        }
    }

    public long calculateTotal(Lottos lottos) {
        long total = 0;
        List<OneLotto> oneLottoList = lottos.getMyLottos();

        for (OneLotto oneLotto : oneLottoList) {
            oneLotto.setBonusCheck(lottos);
            oneLotto.setCorrectCnt();
        }

        for (OneLotto oneLotto : oneLottoList) {
            CorrectNum correctCnt = oneLotto.getCorrectCnt();
            total += correctCnt.getMoney();
        }
        return total;
    }

    public String rateOfReturn(int balance, long total) {
        String result = String.format("%.2f", (double) total / (double) balance);
        return result;
    }

}
