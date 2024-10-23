package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets() {
        this.tickets = new ArrayList<>();
    }

    public List<LottoTicket> getTickets(){
        return Collections.unmodifiableList(tickets);
    }

    public int ticketsCount(){
        return tickets.size();
    }

    public void addTicket(LottoTicket lottoTicket){
        if (lottoTicket == null) {
            throw new IllegalArgumentException("로또 티켓은 비어있을 수 없습니다.");
        }

        tickets.add(lottoTicket);
    }

    public static LottoTickets generateAutomaticTickets(int automaticLottoTicketCount) {
        LottoTickets automaticLottoTickets = new LottoTickets();

        while(automaticLottoTickets.ticketsCount() < automaticLottoTicketCount){
            automaticLottoTickets.addTicket(LottoTicket.generateAutomaticNumbers());
        }

        return automaticLottoTickets;
    }

    public void generateLottoTickets(LottoTickets manualLottoTickets, LottoTickets automaticLottoTickets) {

        mergeAnotherTicketsToLottoTickets(manualLottoTickets);
        mergeAnotherTicketsToLottoTickets(automaticLottoTickets);

    }

    private void mergeAnotherTicketsToLottoTickets(LottoTickets anotherTickets){
        tickets.addAll(anotherTickets.getTickets());
    }

    public void checkWinningTickets(Map<LottoRank, Integer> winningResult,
                                    LottoTicket winningNumbers, LottoNumber bonusNumber){

        for (LottoTicket ticket : tickets) {

            LottoRank rank = ticket.checkWinningTicketConditionAndReturnRank(winningNumbers,bonusNumber);

            winningResult.put(rank,winningResult.get(rank)+1);
        }

    }

}
