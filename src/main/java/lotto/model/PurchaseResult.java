package lotto.model;

import java.util.Objects;

public class PurchaseResult {
    private final Lotto manualLotto;
    private final Lotto autoLotto;
    private final Lotto allLotto;
    private final Money purchaseAmount;

    public PurchaseResult(Lotto manualLotto, Lotto autoLotto, Lotto allLotto, Money purchaseAmount) {
        this.manualLotto = manualLotto;
        this.autoLotto = autoLotto;
        this.allLotto = allLotto;
        this.purchaseAmount = purchaseAmount;
    }

    public Lotto getManualLotto() {
        return manualLotto;
    }

    public Lotto getAutoLotto() {
        return autoLotto;
    }

    public Lotto getAllLotto() {
        return allLotto;
    }

    public Money getPurchaseAmount() {
        return purchaseAmount;
    }

    public int getManualCount() {
        return manualLotto.getNumbers().size();
    }

    public int getAutoCount() {
        return autoLotto.getNumbers().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseResult that = (PurchaseResult) o;
        return Objects.equals(manualLotto, that.manualLotto) &&
            Objects.equals(autoLotto, that.autoLotto) &&
            Objects.equals(allLotto, that.allLotto) &&
            Objects.equals(purchaseAmount, that.purchaseAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualLotto, autoLotto, allLotto, purchaseAmount);
    }
}
