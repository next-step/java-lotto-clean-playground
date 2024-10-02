package utils;

import java.util.*;

public class WinningResult {

    public final static Map<String, Integer> resultMap = new HashMap<>();
    public final static String MATCH_THREE = "3";
    public final static String MATCH_FOUR = "4";
    public final static String MATCH_FIVE = "5";
    public final static String MATCH_SIX = "6";

    public static Map<String, Integer> analizeResultToMap(List<Integer> resultList) {
        List<String> keys = Arrays.asList(
                MATCH_THREE,
                MATCH_FOUR,
                MATCH_FIVE,
                MATCH_SIX);

        for (String key : keys) {
            int count = Collections.frequency(resultList, Integer.parseInt(key));
            resultMap.put(key, count);
        }

        return resultMap;
    }

    public static int calculateTotal() {
        int sum = 0;

        sum += resultMap.get(MATCH_THREE) * 5000;
        sum += resultMap.get(MATCH_FOUR) * 50000;
        sum += resultMap.get(MATCH_FIVE) * 1500000;
        sum += resultMap.get(MATCH_SIX) * 2000000000;

        return sum;
    }

    public static String calculateProfitability(int inputMoney) {
        double profitability = (double) WinningResult.calculateTotal() / inputMoney;

        return String.format("%.2f",profitability);
    }
}
