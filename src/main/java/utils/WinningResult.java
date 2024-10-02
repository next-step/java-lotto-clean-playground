package utils;

import domain.Rank;

import java.util.*;

public class WinningResult {

    public final static Map<Rank, Integer> resultMap = new HashMap<>();

    public static Map<Rank, Integer> analizeResultToMap(List<Rank> resultList) {

        for (Rank rank : Rank.values()) {
            int count = Collections.frequency(resultList,rank);
            resultMap.put(rank, count);
        }

        return resultMap;
    }

    public static int calculateTotal() {
        int sum = 0;

        for (Rank rank : resultMap.keySet()){
            sum += resultMap.get(rank) * rank.getPrizeMoney();
        }

        return sum;
    }

    public static String calculateProfitability(int inputMoney) {
        double profitability = (double) WinningResult.calculateTotal() / inputMoney;

        return String.format("%.2f",profitability);
    }
}
