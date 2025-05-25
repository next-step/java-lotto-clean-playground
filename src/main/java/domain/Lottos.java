package domain;

import java.util.List;
import java.util.stream.Stream;

public class Lottos {
    //구매한 로또들의 내역
    //일급 컬렉션
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }

    public static Lottos merge(Lottos manual, Lottos auto) {
        List<Lotto> combined = Stream.concat(
                manual.getLottos().stream(),
                auto.getLottos().stream()
        ).toList();
        return new Lottos(combined);
    }
}


