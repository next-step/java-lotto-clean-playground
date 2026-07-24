package domain.lotto;

import domain.money.PurchaseAmount;
import domain.number.LottoNumberGenerator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public List<Lotto> buy(int purchaseAmount) {
        return buy(PurchaseAmount.from(purchaseAmount)).values();
    }

    public Lottos buy(PurchaseAmount purchaseAmount) {
        return new Lottos(createLottos(purchaseAmount.lottoCount()));
    }

    private List<Lotto> createLottos(int purchaseCount) {
        return IntStream.range(0, purchaseCount)
                .mapToObj(index -> createLotto())
                .collect(Collectors.toList());
    }

    private Lotto createLotto() {
        return new Lotto(lottoNumberGenerator.generate());
    }
}
