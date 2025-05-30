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

    public static LottoList generateLottoList(List<List<Integer>> manaulNumbers, int cnt) {
        List<Lotto> lottoList = new ArrayList<>();
        //수동 로또 생성
        for (List<Integer> manaulNumber : manaulNumbers) {
            lottoList.add(Lotto.generateManualLotto(manaulNumber));
        }
        //자동로또 생성
        for (int i = 0; i < cnt; i++) {
            lottoList.add(Lotto.generateAutoLotto());
        }
        return new LottoList(lottoList);
    }

    public List<Lotto> getLottoLists() {
        return Collections.unmodifiableList(lottoLists);
    }
}
