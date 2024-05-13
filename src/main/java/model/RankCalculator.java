package model;

import java.util.ArrayList;
import java.util.List;

public class RankCalculator {
    private final List<Integer> ans;
    private final int bonusBall;
    private final List<RankType> rankTypeList = new ArrayList<>();

    public RankCalculator(List<Integer> ans, int bonusBall) {
        this.ans = ans;
        this.bonusBall = bonusBall;
    }

    private void calculateEachLottoCorrectNumbers(LottoNumber lottoNumber) {
        List<Integer> numbers = lottoNumber.getNumbers();
        long count = numbers.stream().filter(ans::contains).count();

        RankType rankType = RankType.getLottoRank((int)count, lottoNumber.hasBonusBall(bonusBall));
        if(rankType == null){
            return;
        }
        rankTypeList.add(rankType);
    }

    public void calculateAllLottoCorrectNumbers(Lotto lotto) {
        List<LottoNumber> lottoNumberList = lotto.getLottoNumberList();
        for (LottoNumber lottoNumber : lottoNumberList) {
            calculateEachLottoCorrectNumbers(lottoNumber);
        }
    }

    private double calculateTotalProfit() {
        double total = 0;
        for (RankType rankType : rankTypeList) {
            total += rankType.getPrize();
        }
        return total;
    }

    public double calculateRateOfReturn(int balance) {
        double total = calculateTotalProfit();
        return total / balance;
    }

    public List<RankType> getRankTypeList() {
        return rankTypeList;
    }
}
