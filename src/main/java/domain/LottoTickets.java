package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(int count){
        this.tickets = new ArrayList<>();
        for(int i=0;i<count;i++){
            List<Integer> lottoList = NumberShuffle.CreateList();
            NumberShuffle.shuffle(lottoList);
            List<Integer> lottoNumbers = NumberShuffle.getLottoNumbers(lottoList);
            NumberShuffle.sortLottoNumber(lottoNumbers);
            tickets.add(new Lotto(lottoNumbers));
        }
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
