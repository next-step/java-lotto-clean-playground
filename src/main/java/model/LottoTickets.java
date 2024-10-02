package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets() {
        this.tickets = new ArrayList<>();
    }

    public void addTicket(LottoTicket lottoTicket){
        if (lottoTicket == null) {
            throw new IllegalArgumentException("로또 티켓은 비어있을 수 없습니다.");
        }
        tickets.add(lottoTicket);
    }

    public List<LottoTicket> getTickets(){
        return Collections.unmodifiableList(tickets);
    }

    public int ticketsCount(){
        return tickets.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LottoTicket ticket : tickets) {
            sb.append("[").append(ticket).append("]\n");
        }
        return sb.toString();
    }
}
