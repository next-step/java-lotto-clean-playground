package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.LottoNumber.MIN_NUMBER;
import static domain.LottoNumber.MAX_NUMBER;
import static domain.LottoTicket.TICKET_LENGTH;

public class RandomLottoTicketGenerator implements LottoTicketGenerator {
    @Override
    public LottoTicket generate() {
        List<LottoNumber> lottoNumberList = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            lottoNumberList.add(LottoNumber.valueOf(i));
        }
        Collections.shuffle(lottoNumberList);
        return new LottoTicket(lottoNumberList.subList(0, TICKET_LENGTH));
    }
}
