package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoList {

    private final List<Lotto> lottoList;
    RandomLottoNumberGenerator generator = new RandomLottoNumberGenerator();

    public LottoList(Integer lottoCount, RandomLottoNumberGenerator generator) {
        this.lottoList = new ArrayList<>();
        this.generator = generator; // 이미 생성된 RandomLottoNumberGenerator를 사용
        for (int i = 0; i < lottoCount; i++) {
            this.lottoList.add(new Lotto(generator));
        }
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }
}
