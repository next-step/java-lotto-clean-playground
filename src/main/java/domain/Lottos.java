package domain;

import java.util.List;

public class Lottos {
    private static final int MIN_LOTTO_COUNT = 1;
    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottoList, int purchaseAmount) {
        validateUnit(purchaseAmount);
        validateLottoCount(purchaseAmount);
        this.lottos = List.copyOf(lottoList);
    }

    public List<Integer> calculateMatchCounts(List<Integer> winningNumbers) {
        return lottos.stream()
                .map(lotto -> lotto.countMatchingNumbers(winningNumbers))
                .toList();
    }

    private void validateLottoCount(int purchaseAmount) {
        if (purchaseAmount < MIN_LOTTO_COUNT * LOTTO_PRICE) {
            throw new IllegalArgumentException(
                    String.format("로또는 최소 %d장 이상 구매할 수 있습니다.", MIN_LOTTO_COUNT)
            );
        }
    }

    private void validateUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE > 0) {
            throw new IllegalArgumentException("로또는 천 원 단위로 구매 가능합니다.");
        }
    }
}
