package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> userLottos;

    public Lottos() {
        this.userLottos = new ArrayList<>();
    }
    public Lottos(List<Lotto> lottos) {
        this.userLottos = lottos;
    }

    public void makeManualLottos(List<String> manualInputs) {
        for (String manualInput : manualInputs) {
            Lotto manualLotto = new Lotto(LottoParser.parseInput(manualInput));
            this.userLottos.add(manualLotto);
        }
    }

    public void makeAutomaticLottos(int automaticLottoCount) {
        for (int i = 0; i < automaticLottoCount; i++) {
            Lotto automaticLotto = new Lotto();
            this.userLottos.add(automaticLotto);
        }
    }

    public int size() {
        return userLottos.size();
    }
    public List<Lotto> getLottos() {
        return List.copyOf(userLottos);
    }
}
