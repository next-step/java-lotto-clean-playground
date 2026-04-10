package model;

import java.util.ArrayList;
import java.util.List;


public class LottoTickets {

    private final List<LottoTicket> tickets = new ArrayList<>();
    private final List<LottoTicket> manualTickets = new ArrayList<>();
    private final List<LottoTicket> autoTickets = new ArrayList<>();

    public void buyAutoTickets(int quantity) {
        for (int i = 1; i <= quantity; i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            LottoTicket lottoTicket = new LottoTicket(randomNumberGenerator.getRandomNumberList());
            tickets.add(lottoTicket);
            autoTickets.add(lottoTicket);
        }
    }

    public void buyManualTickets(List<LottoTicket> inputTickets) {
        tickets.addAll(inputTickets);
        manualTickets.addAll(inputTickets);
    }


    public List<LottoTicket> getTickets() {
        return List.copyOf(tickets);
    }

    public List<LottoTicket> getManualTickets() {
        return List.copyOf(manualTickets);
    }

    public List<LottoTicket> getAutoTickets() {
        return List.copyOf(autoTickets);
    }

}
