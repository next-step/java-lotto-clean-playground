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

        for (Lotto lottoRow : receipt.lottoRows()) {
            LottoResult result = getLottoResult(lottoRow);
            counts.countLottoResult(result);
        }

        return counts;
    }

    private LottoResult getLottoResult(Lotto lottoRow) {
        Set<LottoNumber> numbers = new HashSet<>(drawnLotto.numbers());
        numbers.retainAll(lottoRow.numbers());

        int matchingCount = numbers.size();

        if (matchingCount == 1 || matchingCount == 2){
            return LottoResult.NONE;
        }
        if (matchingCount == 0){
            return LottoResult.NONE;
        }
        if (matchingCount == 3){
            return LottoResult.THREE;
        }
        if (matchingCount == 4){
            return LottoResult.FOUR;
        }
        if (matchingCount == 5){
            return LottoResult.FIVE;
        }
        if (matchingCount == 6){
            return LottoResult.SIX;
        }
        throw new IllegalStateException("나올 수 없는 경우입니다.: " + matchingCount);

    }


    public int getCount(LottoResult result) {
        return counts.getCount(result);
    }

    public float getRateOfReturn() {
        int sumOfReturn = counts.getSumOfReturn();
        return (float) sumOfReturn / receipt.totalPrice();
    }
}
