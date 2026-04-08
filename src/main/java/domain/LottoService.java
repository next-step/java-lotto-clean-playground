package domain;

import util.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    public static final int TICKET_PRICE = 1000;
    LottoNumberGenerator lottoNumberGenerator;

    public LottoService(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public List<LottoTicket> buyTickets(int money) {
        validateMoney(money);
        int ticketCount = money/ TICKET_PRICE;

        List<LottoTicket> tickets = new ArrayList<>();

        for(int i = 0; i < ticketCount; i++){
            tickets.add(generateTicket());
        }

        return tickets;
    }

    private LottoTicket generateTicket() {
        return new LottoTicket(lottoNumberGenerator.generateNumbers());
    }

    private void validateMoney(int money) {
        if(money < TICKET_PRICE) {
            throw new IllegalArgumentException("1000원 이상의 금액을 입력해주세요.");
        }
    }
}
