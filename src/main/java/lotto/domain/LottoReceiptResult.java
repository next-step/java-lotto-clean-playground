package lotto.domain;

import java.util.Map;

public record LottoReceiptResult(LottoReceipt receipt, Map<LottoResult, Integer> numberCounts) {
    public int getCount(LottoResult result) {
        return numberCounts.getOrDefault(result, 0);
    }

    public Rate getRateOfReturn() {
        int sumOfReturn = getSumOfReturn();
        return new Rate((float) sumOfReturn / receipt.totalPrice());
    }

    public int getSumOfReturn() {
        return numberCounts.entrySet().stream()
                .mapToInt(entry -> {
                    LottoResult result = entry.getKey();
                    int count = entry.getValue();
                    return result.reward * count;
                })
                .sum();
    }
}
