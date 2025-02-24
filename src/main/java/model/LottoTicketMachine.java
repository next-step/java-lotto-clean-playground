package model;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketMachine {

    private static final int LOTTO_TICKET_PRICE = 1000;
    private final int ticketCount;
    private final LottoGenerator lottoGenerator;
    private final List<Lotto> lottery = new ArrayList<>();

    public LottoTicketMachine(int purchaseAmount, LottoGenerator lottoGenerator) {
        this.ticketCount = purchaseAmount / LOTTO_TICKET_PRICE;
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> generateLottery() {
        for (int i = 0; i < this.ticketCount; i++) {
            lottery.add(lottoGenerator.generateLottoNumbers());
        }

        return lottery;
    }

    public int getTicketCount() {
        return ticketCount;
    }
}
