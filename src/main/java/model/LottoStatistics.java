package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoStatistics {
    private final List<Integer> collectNumber;
    private List<Integer> matchedCount;

    private final List<Double> prizeMoney = new ArrayList<>(
            Arrays.asList(5000.0, 50000.0, 1500000.0, 200000000.0));


    public LottoStatistics(List<Integer> collectNumber) {

        validateCollectNumber(collectNumber);
        this.collectNumber = collectNumber;
        matchedCount = new ArrayList<>(Collections.nCopies(7, 0));
    }


    private void validateCollectNumber(List<Integer> collectNumber) {

        if (collectNumber.size() != 6) throw new IllegalArgumentException("[ERROR] 6개의 당첨번호를 입력하세요");

        collectNumber.stream().
                forEach(number -> {
                    validateSameNumber(collectNumber, number);
                    if (number < 1 || number > 45)
                        throw new IllegalArgumentException("[ERROR] 1에서 45까지의 숫자만 입력하세요");
                });

    }

    private void validateSameNumber(final List<Integer> collectNumber, final int number) {

        int count = 0;

        for (int i = 0; i < collectNumber.size(); i++) {
            if (collectNumber.get(i) == number) count++;
        }

        if (count >= 2) throw new IllegalArgumentException("[ERROR] 서로 다른 숫자만 입력하세요");
    }

    public void configureMatchedCount(final List<Lotto> haveLottos) {

        for (Lotto lotto : haveLottos) {
            int currentValue = matchedCount.get(lotto.getCollectedCount());
            matchedCount.set(lotto.getCollectedCount(), currentValue + 1);
        }
    }

    public double calculateRatetoReturn(final int inputMoney) {

        double sumMoney = 0;
        for (int i = 3; i <= 6; i++) {
            sumMoney += (double) matchedCount.get(i) * prizeMoney.get(i - 3);
        }

        return sumMoney / (double) inputMoney;
    }


    public List<Integer> getCollectNumber() {
        return collectNumber;
    }

    public List<Integer> getMatchedCount() {
        return matchedCount;
    }

    public List<Double> getPrizeMoney() {
        return prizeMoney;
    }
}

