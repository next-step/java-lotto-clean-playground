package domain;

import java.util.List;
import java.util.stream.Stream;

public class LottoGroup {

    private final List<Lotto> lottos;

    public LottoGroup(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<List<Integer>> getAllLottoNumbers(){
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }

    public static LottoGroup combineLottoGroup(LottoGroup firstGroup, LottoGroup secondGroup){
        List<Lotto> combineLottoNumbers = Stream.of(firstGroup, secondGroup)
                .flatMap(group -> group.getLottos().stream())
                .toList();

        return new LottoGroup(combineLottoNumbers);
    }
}
