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

    public Lottos buy(PurchaseAmount purchaseAmount, Lottos manualLottos) {
        validateManualLottos(purchaseAmount, manualLottos);
        return manualLottos.addAll(createLottos(autoPurchaseCount(purchaseAmount, manualLottos)));
    }

    private void validateManualLottos(PurchaseAmount purchaseAmount, Lottos manualLottos) {
        if (manualLottos.size() > purchaseAmount.lottoCount()) {
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 넘을 수 없습니다.");
        }
    }

    private int autoPurchaseCount(PurchaseAmount purchaseAmount, Lottos manualLottos) {
        return purchaseAmount.lottoCount() - manualLottos.size();
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
