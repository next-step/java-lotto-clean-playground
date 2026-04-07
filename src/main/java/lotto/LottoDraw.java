package lotto;

import java.util.HashSet;
import java.util.Set;

public class LottoDraw {
    private final Lotto drawnLotto;
    private final LottoReceipt receipt;
    private final MatchingCounts counts;

    public LottoDraw(Lotto drawnLotto, LottoReceipt receipt) {
        this.drawnLotto = drawnLotto;
        this.receipt = receipt;

        this.counts = getNumberCount();
    }

    private MatchingCounts getNumberCount() {
        MatchingCounts counts = new MatchingCounts();

        for (Lotto lottoRow : receipt.lottos().getLottos()) {
            LottoResult result = getLottoResult(lottoRow);
            counts.countLottoResult(result);
        }

        return counts;
    }

    private LottoResult getLottoResult(Lotto lottoRow) {
        Set<LottoNumber> numbers = new HashSet<>(drawnLotto.numbers());
        numbers.retainAll(lottoRow.numbers());

        int matchingCount = numbers.size();

        return LottoResult.valueOf(matchingCount);
    }

    public int getCount(LottoResult result) {
        return counts.getCount(result);
    }

    public float getRateOfReturn() {
        int sumOfReturn = counts.getSumOfReturn();
        return (float) sumOfReturn / receipt.totalPrice();
    }
}
