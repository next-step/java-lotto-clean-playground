package domain.lotto;

import domain.common.Money;
import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    private static final int LOTTO_PRICE = 1000;
    
    private final LottoGenerator lottoGenerator;

    public LottoStore(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<LottoTicket> sellLottos(Money money) {

        checkMoneyUnit(money);
        int count = getCount(money);

        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            LottoTicket issuedTicket = lottoGenerator.issue();
            lottoTickets.add(issuedTicket);
        }

        return lottoTickets;
    }

    private void checkMoneyUnit(Money money) {
        if (money.getAmount() % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("1000원 단위의 돈만 받음");
        }
    }

    private int getCount(Money money) {
        return money.getAmount() / LOTTO_PRICE;
    }
}
