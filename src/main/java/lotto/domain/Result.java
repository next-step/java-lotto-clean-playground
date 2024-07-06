package lotto.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lotto.Rank;

public class Result {
    Map<Rank, Integer> result = new LinkedHashMap<>();

    public Result() {
        makeEmptyResult();
    }

    private void makeEmptyResult() {
        List<Rank> ranks = List.of(Rank.values());
        for (int i = ranks.size() - 1; i >= 0; i--) {
            Rank rank = ranks.get(i);
            if (rank != Rank._NO_PLACE) {
                result.put(rank, 0);
            }
        }
    }

    public void addResult(Rank rank) {
        System.out.println(rank);
        System.out.println(result.get(rank));
        result.put(rank, result.get(rank) + 1);
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
