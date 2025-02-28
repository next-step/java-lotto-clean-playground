package service;

import domain.*;
import java.util.stream.IntStream;

public class LottoService {
    private final static int LOTTO_PRICE = 1_000;

    public void validatePurchaseAmount(Amount purchaseAmount) {
        if (purchaseAmount.getAmount() < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 구매 금액은 " + LOTTO_PRICE + "원 입니다.");
        }

        if (purchaseAmount.getAmount() % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액 단위는 " + LOTTO_PRICE + "원 입니다.");
        }
    }

    public LottoCount calculateLottoAmount(Amount purchaseAmount) {
        return new LottoCount(purchaseAmount.getAmount() / LOTTO_PRICE);
    }

    public Lottos createLottos(LottoCount lottoCount) {
        return new Lottos(IntStream.range(0, lottoCount.getCount())
                .mapToObj(i -> LottoMachine.getRandomLotto())
                .toList());
    }

    public LottoStatistics calculateStatistics(Lottos lottos, WinningLottoNumbers winningNumbers) {
        return new LottoStatistics(lottos, winningNumbers);
    }
}
