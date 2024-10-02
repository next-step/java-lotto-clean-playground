package service;

import model.LottoTicket;
import model.LottoTickets;

public class LottoTicketsGenerator {

    public static LottoTickets generateTickets(LottoTickets manualLottoTickets, int totalLottoTicketCount) {
        LottoTickets lottoTickets = new LottoTickets();

        generateManualTickets(manualLottoTickets, lottoTickets);
        generateAutomaticTickets(totalLottoTicketCount, lottoTickets);

        return lottoTickets;
    }

    private static void generateManualTickets(LottoTickets manualLottoTickets, LottoTickets lottoTickets) {
        for (LottoTicket ticket : manualLottoTickets.getTickets()) {
            lottoTickets.addTicket(ticket);
        }
    }


    private static void generateAutomaticTickets(int totalLottoTicketCount, LottoTickets lottoTickets) {
        while(lottoTickets.ticketsCount() < totalLottoTicketCount){
            lottoTickets.addTicket(AutomaticLottoNumberGenerator.generateAutomaticNumbers());
        }
    }
}
