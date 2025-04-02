package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final Map<Integer, Integer> matchCountMap = new HashMap<>();

    protected void calculate(LottoList lottoList, List<LottoNumber> winningNumbers) {
        matchCountMap.clear();

        for (Lotto lotto : lottoList.getLottoList()) {
            int matchCount = lotto.calculateMatchCount(winningNumbers);

            if (matchCount >= 3 && matchCount <= 6) {
                matchCountMap.put(matchCount, matchCountMap.getOrDefault(matchCount, 0) + 1);
            }
        }
    }

    public Map<Integer, Integer> getMatchCountMap() {
        return Collections.unmodifiableMap(matchCountMap);
    }
}
