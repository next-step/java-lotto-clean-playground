package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import exception.LottoNotEnoughMoneyException;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    public Lottos buyLotto(Money money, int manualCount, List<LottoNumbers> manualLottoNumbers) {
        validateEnoughMoney(money, manualCount);
        Lottos manualLottos = buyManualLottos(manualLottoNumbers);
        int autoCount = money.getAmount() / LOTTO_PRICE - manualCount;
        Lottos autoLottos = buyAutoLottos(autoCount);
        return combineLottos(manualLottos, autoLottos);
    }

    private void validateEnoughMoney(Money money, int manualLottoCount) {
        if (money.getAmount() < LOTTO_PRICE) {
            throw new LottoNotEnoughMoneyException("로또 구매 금액은 최소 " + LOTTO_PRICE + "원 이상이어야 합니다.");
        }
        if (money.getAmount() < LOTTO_PRICE * manualLottoCount) {
            throw new LottoNotEnoughMoneyException("수동으로 구매할 로또 개수보다 적은 금액을 입력하셨습니다.");
        }
    }

    private Lottos buyManualLottos(List<LottoNumbers> manualNumbers) {
        List<Lotto> lottos = manualNumbers.stream()
            .map(Lotto::new)
            .toList();
        return new Lottos(lottos);
    }

    private Lottos buyAutoLottos(int count) {
        List<Lotto> lottos = IntStream.range(0, count)
            .mapToObj(i -> new Lotto())
            .toList();
        return new Lottos(lottos);
    }

    private Lottos combineLottos(Lottos manualLottos, Lottos autoLottos) {
        List<Lotto> mergedLottos = new ArrayList<>(manualLottos.getLottos());
        mergedLottos.addAll(autoLottos.getLottos());
        return new Lottos(mergedLottos);
    }
}
