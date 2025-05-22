package domain.lotto;

import java.util.ArrayList;
import java.util.List;
import strategy.LottoNumberGenerator;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public static Lottos generate(final int count, final LottoNumberGenerator generator) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(Lotto.create(generator));
        }
        return new Lottos(lottoList);
    }

    public List<Lotto> getValues() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }
}
