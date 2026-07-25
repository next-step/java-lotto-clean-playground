package domain;

import java.util.Collections;
import java.util.Map;

public class LottoCountByMatchNumber {
    private final Map<Integer, Integer> countByMatchNumber;

    public LottoCountByMatchNumber(Map<Integer, Integer> countByMatchNumber) {
        this.countByMatchNumber = countByMatchNumber;
    }

    public Integer get(Integer matchNumber) {
        return countByMatchNumber.get(matchNumber);
    }

    public Map<Integer, Integer> getLottoCountByMatchNumber() {
        return Collections.unmodifiableMap(countByMatchNumber);
    }
}
