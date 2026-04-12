package domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoService {
    public static final int TICKET_PRICE = 1000;

    public LottoService() {
    }

    public TicketBundle buyTickets(int money, List<List<Integer>> manualBuyTickets) {
        int manalTicketNumber = manualBuyTickets.size();
        money = money - manalTicketNumber *TICKET_PRICE;

        validateMoney(money);
        int ticketCount = money/ TICKET_PRICE;

        TicketBundle tickets = new TicketBundle();

        for(List<Integer> ticketNumbers:manualBuyTickets){
            LottoTicket ticket = new LottoTicket(ticketNumbers);
            tickets.add(ticket);
        }

        for(int i=0;i<ticketCount;i++){
            tickets.add(generateTicket());
        }

        return tickets;
    }

    private LottoTicket generateTicket() {
        List<Integer> ticketBasket;
        List<Integer> ticketNumbers;

        ticketBasket = IntStream.rangeClosed(1,45)
                                .boxed()
                                .collect(Collectors.toList());
        Collections.shuffle(ticketBasket);
        ticketNumbers = ticketBasket.subList(0, 6);

        return new LottoTicket(ticketNumbers);
    }

    private void validateMoney(int money) {
        if(money < TICKET_PRICE) {
            throw new IllegalArgumentException("1000원 이상의 금액을 입력해주세요.");
        }
    }
}
