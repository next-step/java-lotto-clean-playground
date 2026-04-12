package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.LottoNumber.MIN_NUMBER;
import static domain.LottoNumber.MAX_NUMBER;
import static domain.LottoTicket.TICKET_LENGTH;

public class RandomLottoTicketGenerator implements LottoTicketGenerator {
    private static final List<LottoNumber> lottoNumberList = new ArrayList<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            lottoNumberList.add(LottoNumber.valueOf(i));
        }
    }

    @Override
    public LottoTicket generate() {
        Collections.shuffle(lottoNumberList);
        return new LottoTicket(lottoNumberList.subList(0, TICKET_LENGTH));
    }
}
