package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoList {

    private static final int PRICE_PER_LOTTO = 1000;

    private final List<Lotto> lottoList;
    private final int lottoCount; //추가 구매가 허용되는 상황이라면 non-final
    private LottoStatistics statistics;

    public LottoList(long purchaseAmount, LottoNumberGenerator generator) {
        validateAmount(purchaseAmount);
        this.lottoList = new ArrayList<>();
        this.lottoCount = (int) purchaseAmount / PRICE_PER_LOTTO;
        for (int i = 0; i < lottoCount; i++) {
            this.lottoList.add(new Lotto(generator));
        }
    }

    private void validateAmount(long purchaseAmount) {
        if (purchaseAmount < PRICE_PER_LOTTO) {
            throw new IllegalArgumentException("로또 최소 구매 금액은 1000원입니다.");
        }
        if (purchaseAmount % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException("로또 금액은 1000원 단위여야 합니다.");
        }
    }

    public LottoStatistics calculateStatistics(List<LottoNumber> winningNumbers) {
        if(statistics == null) {
            statistics = new LottoStatistics();
            statistics.calculate(this, winningNumbers);
        }
        return statistics;
    }

    public int calculatePrize(List<LottoNumber> winningNumbers) {
        LottoStatistics statistics = calculateStatistics(winningNumbers); // 캐시된 통계 사용
        PrizeCalculator prizeCalculator = new PrizeCalculator();
        prizeCalculator.calculate(statistics.getMatchCountMap());
        return prizeCalculator.getTotalPrize();
    }

    public List<Lotto> getLottoList() {
        return Collections.unmodifiableList(lottoList);
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
