package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {
    private final List<Lotto> randomLottos;
    private final List<Lotto> manualLottos;

    public LottoTickets(List<Lotto> randomLottos, List<Lotto> manualLottos) {
        this.randomLottos = randomLottos;
        this.manualLottos = manualLottos;
    }

    public List<Lotto> getManualLottos() {
        return manualLottos;
    }

    public List<Lotto> getRandomLottos() {
        return randomLottos;
    }

    public List<Lotto> getAllLottos() {
        return Stream.concat(manualLottos.stream(), randomLottos.stream())
                .collect(Collectors.toList());
    }
}
