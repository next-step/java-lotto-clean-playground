package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private int lottoAmount;
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int lottoAmount) {
        this.lottoAmount = lottoAmount;

    }

    public void publishLottos(){
        for(int i = 0; i < lottoAmount; i++){
            lottos.add(new Lotto());
        }
    }

    public int getLottoAmount() {
        return lottoAmount;
    }
}
