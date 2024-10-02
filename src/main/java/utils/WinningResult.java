package utils;

import java.util.*;

public class WinningResult {
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

        Map<Integer, Integer> resultMap = new HashMap<>();

        for (Integer key : keys) {
            int count = Collections.frequency(resultList, key);
            resultMap.put(key, count);
        }

        return resultMap;
    }
}
