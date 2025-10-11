package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTicketBundle {
    private final List<LottoTicket> repository = new ArrayList<>();

    public void addLottoNumbers(LottoTicket lottoTicket) {
        repository.add(lottoTicket);
    }

    public List<LottoTicket> readLottoNumbersRepository() {
        return Collections.unmodifiableList(repository);
    }

}
