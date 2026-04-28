package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {
    private final Lottos lottos;
    private final int totalPrice;
    private final int change;
    private final int manualCount;

    public LottoPurchase(LottoPrice totalPrice, List<Lotto> manualLottos, LottoMaker lottoMaker) {
        this.totalPrice = totalPrice.getPrice();
        this.change = totalPrice.getPrice() % totalPrice.getPricePerLotto();
        this.manualCount = manualLottos.size();

        int totalTicketCount = totalPrice.getPrice() / totalPrice.getPricePerLotto();
        int autoCount = totalTicketCount - manualCount;

        if (autoCount < 0) {
            throw new IllegalArgumentException("구입 금액보다 많은 수동 로또를 선택하셨습니다.");
        }
        List<Lotto> allLottos = new ArrayList<>(manualLottos);

        if (autoCount > 0) {
            Lottos autoLottos = Lottos.from(autoCount, lottoMaker);
            allLottos.addAll(autoLottos.getLottos());
        }

        this.lottos = new Lottos(allLottos);
    }

    public LottoReceipt getReceipt() {
        return new LottoReceipt(lottos, totalPrice);
    }

    public int getAutoCount() {
        return getNumberOfLotto() - manualCount;
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }

    public int getChange() {
        return change;
    }
}
