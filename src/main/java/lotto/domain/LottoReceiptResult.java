package lotto.domain;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LottoReceiptResult {
    private final LottoReceipt receipt;
    private final Map<LottoResult, Integer> numberCounts = new EnumMap<>(LottoResult.class);

    public LottoReceiptResult(WinningLotto win, LottoReceipt receipt) {
        this.receipt = receipt;

        for (Lotto lottoRow : receipt.lottoRows()) {
            LottoResult result = getLottoResult(win, lottoRow);
            int previous = numberCounts.getOrDefault(result, 0);
            numberCounts.put(result, previous + 1);
        }
    }

    private LottoResult getLottoResult(WinningLotto win, Lotto lottoRow) {
        Set<LottoNumber> rowNumbers = new HashSet<>(lottoRow.getNumbers());

        Set<LottoNumber> matchingNumbers = new HashSet<>(win.getNumbers());
        matchingNumbers.retainAll(rowNumbers);

        int matchingCount = matchingNumbers.size();
        return switch (matchingCount) {
            case 0, 1, 2 -> LottoResult.NONE;
            case 3 -> LottoResult.THREE;
            case 4 -> LottoResult.FOUR;
            case 5 -> {
                LottoNumber bonus = win.bonus();
                if (rowNumbers.contains(bonus)) {
                    yield LottoResult.FIVE_BONUS;
                } else {
                    yield LottoResult.FIVE;
                }
            }
            case 6 -> LottoResult.SIX;
            default -> throw new IllegalStateException("나올 수 없는 경우입니다.: " + matchingCount);
        };
    }

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
