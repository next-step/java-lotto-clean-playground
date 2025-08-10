package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {
    private final Lottos randomLottos;
    private final Lottos manualLottos;

    public LottoTickets(Lottos randomLottos, Lottos manualLottos) {
        this.randomLottos = randomLottos;
        this.manualLottos = manualLottos;
    }

    public Lottos getManualLottos() {
        return manualLottos;
    }

    public Lottos getRandomLottos() {
        return randomLottos;
    }

    public List<Lotto> getRandomLottosList() {
        return randomLottos.asList();
    }

    public List<Lotto> getAllLottos() {
        return Stream.concat(manualLottos.stream(), randomLottos.stream())
                .collect(Collectors.toList());
    }
}
