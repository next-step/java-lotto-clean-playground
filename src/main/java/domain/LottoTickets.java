package domain;

import java.util.ArrayList;
import java.util.TreeSet;

public class LottoTickets {
    ArrayList<Lotto> lottoArrayList = new ArrayList<>();

    public ArrayList<Lotto> makeLottos(int lottoTotalCount){
        for (int i = 0; i < lottoTotalCount; i++) {
            lottoArrayList.add(new Lotto());
        }
        return lottoArrayList;
    }

    public TreeSet<Integer> getLottoTreeSet(int lottoTicketNumber){
        return new TreeSet<>(lottoArrayList.get(lottoTicketNumber).getRandomNumberSet());
    }

    public int getSize() {
        return lottoArrayList.size();
    }
}
