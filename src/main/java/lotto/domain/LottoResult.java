package lotto.domain;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult { //결과 자체를 담는 객체 (ex. 1개 일치 >2장, 등등)
    private final Map<Rank, Long> result;

    public LottoResult(Map<Rank, Long> result) {
        this.result = new EnumMap<>(result);
    }
    //받은 Map을 그대로 참조하지 않고 새 EnumMap으로 복사해서 저장하는 것

    public double calculateYield(int investment) { //수익률 계산, 수익률은 소수로 나올 수 있으므로 반환타입은 double
        long totalPrize = result.entrySet().stream() //result는 map임 entrySet은 이걸 (key, value) 쌍 묶음들로 꺼내줌
                .mapToLong(entry -> entry.getKey().getWinningMoney() * entry.getValue())// 등수 상금 x 등수 개수
                .sum();
        return (double) totalPrize / investment; //investment는 로또를 산 금액.
    }

    public Map<Rank, Long> getResult() {
        return result;
    }
}
