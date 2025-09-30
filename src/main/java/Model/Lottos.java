package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private List<Lotto> lottoList;
    private static final int COST_PER_TICKET = 1000;

    public Lottos(int money) {
        int numberOfLottos = money / COST_PER_TICKET;
        this.lottoList = new ArrayList<>();

        for (int i = 0; i < numberOfLottos; i++) {
            lottoList.add(new Lotto()); // new Lotto()가 호출될 때마다 새로운 로또 번호 6개가 생성됩니다.
        }
    }

    public List<Lotto> getLottoList() {
        return Collections.unmodifiableList(lottoList);
    }
}
