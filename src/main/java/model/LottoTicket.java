package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {

    private final List<Lotto> lottos;

    public LottoTicket(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public static LottoTicket merge(ManualLottoTicket manual, AutoLottoTicket auto) {
        List<Lotto> combined = new ArrayList<>();

        if (manual != null && manual.getLottos() != null) {
            combined.addAll(manual.getLottos());
        }

        if (auto != null && auto.getLottos() != null) {
            combined.addAll(auto.getLottos());
        }

        return new LottoTicket(Collections.unmodifiableList(combined));
    }
}
