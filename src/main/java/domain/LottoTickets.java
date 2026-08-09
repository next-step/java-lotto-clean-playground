package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LottoTickets {
    ArrayList<Lotto> lottoArrayList = new ArrayList<>();

    public void addUserSelectedLottos(List<Lotto> userSelectedLottos) {
        lottoArrayList.addAll(userSelectedLottos);
    }

    public void addAutoLottos(int autoCount) {
        for (int i = 0; i < autoCount; i++) {
            lottoArrayList.add(new Lotto());
        }
    }

    public TreeSet<Integer> getLottoTreeSet(int lottoTicketNumber){
        return new TreeSet<>(lottoArrayList.get(lottoTicketNumber).getRandomNumberSet());
    }

    public int getSize() {
        return lottoArrayList.size();
    }
}
