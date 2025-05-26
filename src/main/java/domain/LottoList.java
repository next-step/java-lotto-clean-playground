package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//로또 여러장
public class LottoList {

    private final List<Lotto> lottoLists;

    private LottoList(List<Lotto> lottoLists) {
        this.lottoLists = lottoLists;
    }

    public static LottoList generateLottoList(int cnt) {
        List<Lotto> list = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            list.add(Lotto.generateLotto());
        }
        return new LottoList(list);
    }

    public List<Lotto> getLottoLists() {
        return Collections.unmodifiableList(lottoLists);
    }
}
