package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int numberOfLottos) { //로또 자동생성 생성자
        this.lottos = generateLottos(numberOfLottos);
    }

    //구입한 자동 로또개수만큼 로또 자동 생성
    private List<Lotto> generateLottos(int numberOfAutoLottos){

        List<Lotto> lottoList = new ArrayList<>();
        for (int i=0; i<numberOfAutoLottos; i++){
            lottoList.add(new Lotto());
        }

        return lottoList;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
