package domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    private static final int LOTTO_PRICE = 1000;
    
    private final LottoGenerator lottoGenerator;

    public LottoStore(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<LottoTicket> sellLottos(Money money) {
        int count = getCount(money);

        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            LottoTicket issuedTicket = lottoGenerator.issue();
            lottoTickets.add(issuedTicket);
        }

        return lottoTickets;
    }

    private static int getCount(Money money) {
        return money.getAmount() / LOTTO_PRICE;
    }
}
