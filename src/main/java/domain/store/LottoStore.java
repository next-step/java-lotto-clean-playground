package domain.store;

import domain.lotto.Lottos;
import java.util.List;
import strategy.RandomNumberGenerator;

public class LottoStore {

    private final LottoMachine machine;

    public LottoStore() {
        this.machine = new LottoMachine(new RandomNumberGenerator());
    }

    public LottoReceipt buy(final Cashier cashier, final List<String> manualNumbers) {
        Lottos manual = machine.generateManual(manualNumbers);
        Lottos auto = machine.generateAuto(cashier.getAutoCount());
        return new LottoReceipt(manual, auto);
    }
}
