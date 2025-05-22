package domain;

import domain.generator.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos create(List<List<Integer>> manualNumbers, int autoCount, NumberGenerator generator) {
        List<Lotto> totalLottos = new ArrayList<>();
        for (List<Integer> numbers : manualNumbers) {
            totalLottos.add(new Lotto(() -> numbers.stream().map(LottoNumber::new).toList()));
        }
        for (int i = 0; i < autoCount; i++) {
            totalLottos.add(new Lotto(generator));
        }

        return new Lottos(totalLottos);
    }


    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
