package domain.store;

import domain.lotto.Lottos;

public record LottoReceipt(
        Lottos manual,
        Lottos auto
) {

    public Lottos total() {
        return manual.merge(auto);
    }
}
