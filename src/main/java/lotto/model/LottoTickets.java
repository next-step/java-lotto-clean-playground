package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private List<LottoTicket> tickets = new ArrayList<>();

    public LottoTickets() {
    }

    public String changeTicketsListToStr() {
        String ticketsStr = tickets.stream()
                .map(LottoTicket::getTicketStr)
                .collect(Collectors.joining("\n"));
        return ticketsStr;
    }

    public String createTickets(int NumOfTickets, List<String> passivityNums) {
        for (int i = 0; i < passivityNums.size(); i++) {
            LottoTicket lottoTicket = new LottoTicket(passivityNums.get(i));
            tickets.add(lottoTicket);
        }
        for (int i = passivityNums.size(); i < NumOfTickets; i++) {
            LottoTicket lottoTicket = new LottoTicket();
            tickets.add(lottoTicket);
        }
        return changeTicketsListToStr();
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }

}
