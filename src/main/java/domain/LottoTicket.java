package domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LottoTicket {
    private final Lotto lotto;

    public LottoTicket(Lotto lotto) {
        this.lotto = lotto;
    }

    public Lotto lotto() {
        return lotto;
    }

    public boolean hasBonus(int bonusNumber) {
        return lotto.contains(LottoNumber.of(bonusNumber));
    }

    public List<Integer> numbers() {
        List<Integer> values = new ArrayList<>();
        for (LottoNumber n : lotto.numbers()) {
            values.add(n.value());
        }
        Collections.sort(values);
        return List.copyOf(values);
    }
}
