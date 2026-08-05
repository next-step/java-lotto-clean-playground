package controller;

import model.Rank;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Result {

    EnumMap<Rank, Integer> counts;

    public Result(EnumMap<Rank, Integer> counts) {
        this.counts = counts;
    }

    public float totalRatio(int cost) {
        int totalPrize = totalPrize();

        float ratio = ((float) totalPrize / (float) cost);

        return ratio;
    }

    public int totalPrize() {
        int totalPrize = 0;

        for (Map.Entry<Rank, Integer> entry : counts.entrySet()) {
            Rank rank = entry.getKey();
            totalPrize = totalPrize + rank.getPrize() * entry.getValue();
        }
        return totalPrize;
    }
}
