package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PurchaseLotto {

    public static final int PRICE = 1000;
    private final Money money;
    private final Lotto lotto;

    public PurchaseLotto(Money money, LottoGenerator lottoGenerator,
        List<LottoNumbers> manualLottoNumbers
    ) {
        validate(money, lottoGenerator, manualLottoNumbers);
        List<LottoNumbers> allLotto = mergeLotto(money, lottoGenerator, manualLottoNumbers);

        this.money = money;
        this.lotto = new Lotto(allLotto);
    }

    private void validate(Money money, LottoGenerator lottoGenerator,
        List<LottoNumbers> manualLottoNumbers) {
        Objects.requireNonNull(money);
        Objects.requireNonNull(lottoGenerator);
        Objects.requireNonNull(manualLottoNumbers);
    }

    private List<LottoNumbers> mergeLotto(Money money, LottoGenerator lottoGenerator,
        List<LottoNumbers> manualLottoNumbers) {
        int autoCount = money.getAmount() / PRICE - manualLottoNumbers.size();
        List<LottoNumbers> autoLotto = generateLotto(lottoGenerator, autoCount);

        List<LottoNumbers> allLotto = new ArrayList<>(manualLottoNumbers);
        allLotto.addAll(autoLotto);
        return allLotto;
    }

    public int purchaseCount() {
        return money.getAmount() / PRICE;
    }

    public Lotto getLotto() {
        return lotto;
    }

    private List<LottoNumbers> generateLotto(LottoGenerator generator, int count) {
        return java.util.stream.IntStream.range(0, count)
            .mapToObj(i -> generator.generate())
            .collect(java.util.stream.Collectors.toList());
    }
}
