package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<Lotto> tickets;
    private final int manualCount;
    private final int autoCount;

    public LottoTickets(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        this.manualCount = manualLottos.size();
        this.autoCount = autoLottos.size();

        this.tickets = new ArrayList<>();
        this.tickets.addAll(manualLottos);
        this.tickets.addAll(autoLottos);
    }

    public List<Lotto> getTickets() {
        return new ArrayList<>(tickets);
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getTotalCount() {
        return tickets.size();
    }
}
