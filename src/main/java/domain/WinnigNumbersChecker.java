package domain;

import utils.WinningNumberFomatter;

import java.util.*;
import java.util.stream.Collectors;

public class WinnigNumbersChecker {
    Lottos lottos;
    private final static int MATCH_COUNT_THREE = 3;
    private final static int MATCH_COUNT_FOUR = 4;
    private final static int MATCH_COUNT_FIVE = 5;
    private final static int MATCH_COUNT_SIX = 6;

    private int countMatchingNumbers(List<Integer> lotto) {
        List<Integer> winningNumbers = WinningNumberFomatter.formedWinningNumbers;

        return (int) lotto.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public List<Integer> analizeResultToList() {
        List<Integer> resultList = new ArrayList<>();

        for (Lotto lotto : lottos.getLottos()) {
            resultList.add(countMatchingNumbers(lotto.getLotto()));
        }
        return resultList;
    }

    public Map<Integer, Integer> analizeResultToMap() {
        List<Integer> resultList = analizeResultToList();
        List<Integer> keys = Arrays.asList(MATCH_COUNT_THREE,
                                            MATCH_COUNT_FOUR,
                                            MATCH_COUNT_FIVE,
                                            MATCH_COUNT_SIX);

        Map<Integer,Integer> resultMap = new HashMap<>();

        for(Integer key : keys){
            int count = Collections.frequency(resultList,key);
            resultMap.put(key,count);
        }

        return resultMap;
    }

}
