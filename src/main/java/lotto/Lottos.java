package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validate(lottos);
        this.lottos = List.copyOf(lottos);
    }

    public static Lottos from(int count, LottoMaker lottoMaker) {
        List<Lotto> tempLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tempLottos.add(lottoMaker.makeLotto());
        }
        return new Lottos(tempLottos);
    }

    private void validate(List<Lotto> lottos) {
        if (lottos == null) {
            throw new IllegalArgumentException("로또에 null이 들어왔습니다.");
        }
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("로또가 비어있습니다.");
        }
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
