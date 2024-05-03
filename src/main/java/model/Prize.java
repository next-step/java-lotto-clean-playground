package model;

import java.util.HashMap;
import java.util.Map;

public class Prize {
    private final Map<Integer, Integer> prizeMap;

    public Prize() {
        prizeMap = initializePrizeMap();
    }

    private Map<Integer, Integer> initializePrizeMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(3, 5000);
        map.put(4, 50000);
        map.put(5, 1500000);
        map.put(6, 2000000000);
        return map;
    }

    public int getPrize(int matches) {
        return prizeMap.getOrDefault(matches, 0);
    }
}