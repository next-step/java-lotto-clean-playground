package utils;

import java.util.*;

public class WinningResult {

    public final static Map<Integer, Integer> resultMap = new HashMap<>();
    private final static int MATCH_COUNT_THREE = 3;
    private final static int MATCH_COUNT_FOUR = 4;
    private final static int MATCH_COUNT_FIVE = 5;
    private final static int MATCH_COUNT_SIX = 6;

    public static Map<Integer, Integer> analizeResultToMap(List<Integer> resultList) {
        List<Integer> keys = Arrays.asList(
                MATCH_COUNT_THREE,
                MATCH_COUNT_FOUR,
                MATCH_COUNT_FIVE,
                MATCH_COUNT_SIX);

        for (Integer key : keys) {
            int count = Collections.frequency(resultList, key);
            resultMap.put(key, count);
        }

        return resultMap;
    }

    public static int calculateTotal() {
        int sum = 0;

        sum += resultMap.get(MATCH_COUNT_THREE) * 5000;
        sum += resultMap.get(MATCH_COUNT_THREE) * 50000;
        sum += resultMap.get(MATCH_COUNT_THREE) * 1500000;
        sum += resultMap.get(MATCH_COUNT_THREE) *2000000000;

        return sum;
    }

    public static String calculateProfitability(int inputMoney) {
        double profitability = (double) WinningResult.calculateTotal() /inputMoney;

        return String.format("%.2f",profitability);
    }
}
