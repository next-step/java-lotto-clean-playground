package model;

import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> result;

    public LottoResult(final Map<Rank, Integer> result) {
        this.result = result;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
