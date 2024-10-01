package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int numberOfLottos) {
        this.lottos = generateLottos(numberOfLottos);
    }

    //구입한 로또개수만큼 로또 생성
    private List<Lotto> generateLottos(int numberOfLottos){

        List<Lotto> lottoList = new ArrayList<>();
        for (int i=0; i<numberOfLottos; i++){
            lottoList.add(new Lotto());
        }

        return lottoList;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }


}
