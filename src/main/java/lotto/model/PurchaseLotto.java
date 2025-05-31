package lotto.model;

import java.util.List;
import java.util.Objects;

public class PurchaseLotto {

    public static final int PRICE = 1000;
    private final Money money;
    private final Lottos lottos;

    public PurchaseLotto(Money money, LottoGenerator lottoGenerator) {
        Objects.requireNonNull(money);
        Objects.requireNonNull(lottoGenerator);

        this.money = money;
        this.lottos = new Lottos(generateLottos(lottoGenerator, purchaseCount()));
    }

    public int purchaseCount() {
        return money.getAmount() / PRICE;
    }

    public Lottos getLottos() {
        return lottos;
    }

    private List<LottoNumbers> generateLottos(LottoGenerator generator, int count) {
        return java.util.stream.IntStream.range(0, count)
            .mapToObj(i -> generator.generate())
            .collect(java.util.stream.Collectors.toList());
    }
}
