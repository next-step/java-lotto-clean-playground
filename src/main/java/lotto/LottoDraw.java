package lotto;

import java.util.HashSet;
import java.util.Set;

public class LottoDraw {
    private final Lotto drawnLotto;
    private final LottoReceipt receipt;
    final MatchingCounts counts;

    /**
     * @param drawnLotto 정식 추첨을 통해 뽑힌 일련의 로또 번호이다.
     * @param receipt    결과를 보고자 하는 로또 종이다.
     */
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
        return switch (matchingCount) {
            case 0, 1, 2 -> LottoResult.NONE;
            case 3 -> LottoResult.THREE;
            case 4 -> LottoResult.FOUR;
            case 5 -> LottoResult.FIVE;
            case 6 -> LottoResult.SIX;
            default -> throw new IllegalStateException("나올 수 없는 경우입니다.: " + matchingCount);
        };
    }

    public int getCount(LottoResult result) {
        return counts.getCount(result);
    }

    public float getRateOfReturn() {
        int sumOfReturn = counts.getSumOfReturn();
        return (float) sumOfReturn / receipt.totalPrice();
    }
}
