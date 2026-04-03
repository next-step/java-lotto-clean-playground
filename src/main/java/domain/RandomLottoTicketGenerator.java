package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoTicketGenerator implements LottoTicketGenerator {
    private static final int UPPER_BOUND = 46;
    private static final int LOWER_BOUND = 1;

    @Override
    public LottoTicket generate() {
        List<LottoNumber> lottoNumberList = new ArrayList<>();
        for (int i = LOWER_BOUND; i < UPPER_BOUND; i++) {
            lottoNumberList.add(LottoNumber.valueOf(i));
        }
        Collections.shuffle(lottoNumberList);
        return new LottoTicket(lottoNumberList.subList(0, 6));
    }
}
