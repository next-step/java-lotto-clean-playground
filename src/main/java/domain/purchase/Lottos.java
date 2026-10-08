package domain.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public <T> List<T> map(Function<Lotto, T> mapper) {
        return lottos.stream()
                .map(mapper)
                .toList();
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
