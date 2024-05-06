package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos=new ArrayList<>();

    public Lottos() {
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void addLotto(Lotto lotto){
        lottos.add(lotto);
    }

}
