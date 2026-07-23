package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public List<Lotto> buy(int purchaseAmount) {
        int purchaseCount = purchaseAmount / LOTTO_PRICE;
        return createLotto(purchaseCount);
    }

    private List<Lotto> createLotto(int purchaseCount) {
        return IntStream.range(0, purchaseCount)
                .mapToObj(index -> createLotto())
                .collect(Collectors.toList());
    }

    private Lotto createLotto() {
        return new Lotto(lottoNumberGenerator.generate());
    }
}
