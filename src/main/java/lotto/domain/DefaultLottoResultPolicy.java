package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class DefaultLottoResultPolicy implements LottoResultPolicy {
    private final DefaultLottoResultResolver resolver = new DefaultLottoResultResolver();

    @Override
    public LottoReceiptResult getResult(WinningLotto win, LottoReceipt receipt) {
        Map<LottoResult, Integer> numberCounts = new EnumMap<>(LottoResult.class);

        for (Lotto lottoRow : receipt.lottoRows()) {
            LottoResult result = getResult(win, lottoRow);
            int previous = numberCounts.getOrDefault(result, 0);
            numberCounts.put(result, previous + 1);
        }

        return new LottoReceiptResult(receipt, numberCounts);
    }

    @Override
    public LottoResult getResult(WinningLotto win, Lotto lottoRow) {
        return resolver.getResult(win, lottoRow);
    }
}
