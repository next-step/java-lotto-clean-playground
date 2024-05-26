package domain;

import java.util.ArrayList;
import java.util.List;

public class PassiveLottoTickets {

    private static final int ININTIAL_NUMBER = 0;
    private final List<PassiveLottoTicket> tickets = new ArrayList<>();

    public PassiveLottoTickets(int count, List<Integer> numbers) {
        for (int i = ININTIAL_NUMBER; i < count; i++) {
            tickets.add(new PassiveLottoTicket(numbers));
        }
    }

    public List<PassiveLottoTicket> getTickets() {
        return new ArrayList<>(tickets);
    }
}
