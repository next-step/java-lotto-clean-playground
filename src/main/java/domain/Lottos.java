package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public void addLotto(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public int getSize() {
        return lottos.size();
    }

    public List<String> getStatus() {
        List<String> status = new ArrayList<>();
        for (Lotto lotto : lottos) {
            status.add(lotto.toString());
        }
        return status;
    }
}
