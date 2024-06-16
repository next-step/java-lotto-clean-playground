package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(NumberGenerator numberGenerator, int numberOfLotto) {
        lottos = new ArrayList<>();
        for (int count = 0; count < numberOfLotto; count++) {
            lottos.add(new Lotto(numberGenerator));
        }
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
