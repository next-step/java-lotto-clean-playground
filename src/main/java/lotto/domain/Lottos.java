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

    public static Lottos autoGenerate(LottoCount count, LottoNumberGenerator generator) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count.getCount(); i++) {
            lottoList.add(new Lotto(generator.generateLottoNumbers()));
        }
        return new Lottos(lottoList);
    }

    public static Lottos manualGenerate(List<List<Integer>> inputLottos) {
        List<Lotto> lottoList = new ArrayList<>();
        for (List<Integer> numbers : inputLottos) {
            lottoList.add(new Lotto(numbers));
        }
        return new Lottos(lottoList);
    }

    public Lottos combine(Lottos other) {
        List<Lotto> combinedLottos = new ArrayList<>(this.lottoList);
        combinedLottos.addAll(other.lottoList);

        return new Lottos(combinedLottos);
    }

    public Stream<Lotto> stream() {
        return lottoList.stream();
    }

    public List<Lotto> getLottoList() {
        return Collections.unmodifiableList(lottoList);
    }

}
