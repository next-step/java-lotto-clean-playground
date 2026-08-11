package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LottoTickets {
    ArrayList<Lotto> lottos = new ArrayList<>();

    public void addUserSelectedLottos(List<Lotto> userSelectedLottos) {
        lottos.addAll(userSelectedLottos);
    }

    public void addAutoLottos(int autoCount) {
        for (int i = 0; i < autoCount; i++) {
            lottos.add(new Lotto());
        }
    }

    public TreeSet<Integer> getTicketNumbers(int ticketIndex){
        return new TreeSet<>(lottos.get(ticketIndex).getNumbers());
    }

    public int getSize() {
        return lottos.size();
    }
}
