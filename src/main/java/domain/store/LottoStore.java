package domain.store;

import domain.lotto.Lottos;
import java.util.List;

public class LottoStore {

    private final LottoMachine machine;

    public LottoStore(final LottoMachine machine) {
        this.machine = machine;
    }

    public LottoReceipt buy(final Cashier cashier, final List<String> manualInputs) {
        Lottos manual = machine.generateManual(manualInputs);
        Lottos auto = machine.generateAuto(cashier.getAutoCount());
        return new LottoReceipt(manual, auto);
    }
}
