package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottoList;

    public Lottos(List<Lotto> lottoList) {
        this.lottoList = new ArrayList<>(lottoList);
    }

    public static Lottos generate(int count, LottoNumberGenerator generator) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(generator.generateLottoNumbers()));
        }
        return new Lottos(lottoList);
    }

    public Stream<Lotto> stream() {
        return lottoList.stream();
    }

    public List<Lotto> getLottoList() {
        return Collections.unmodifiableList(lottoList);
    }

}
