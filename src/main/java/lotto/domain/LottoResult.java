package lotto.domain;
import java.util.Collections;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Long> result;

    public LottoResult(Map<Rank, Long> result) {
        this.result = result;
    }


    public double calculateYield(int investment) { //수익률 계산, 수익률은 소수로 나올 수 있으므로 반환타입은 double
        long totalPrize = result.entrySet().stream() //result는 map임 entrySet은 이걸 (key, value) 쌍 묶음들로 꺼내줌
                .mapToLong(entry -> entry.getKey().getWinningMoney() * entry.getValue())// 등수 상금 x 등수 개수
                .sum();
        return (double) totalPrize / investment; //investment는 로또를 산 금액.
    }

    public Map<Rank, Long> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
