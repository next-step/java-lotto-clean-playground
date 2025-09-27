package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(LottoTicketCount count) {
        this.tickets = new ArrayList<>();
        for (int i = 0; i < count.getCount(); i++) {
            List<Integer> lottoList = NumberShuffle.CreateList();
            NumberShuffle.shuffle(lottoList);
            List<Integer> lottoNumbers = NumberShuffle.getLottoNumbers(lottoList);
            NumberShuffle.sortLottoNumber(lottoNumbers);

            List<LottoNumber> lottoNumberObjects = lottoNumbers.stream()
                    .map(LottoNumber::new).collect(Collectors.toList());

            tickets.add(new Lotto(lottoNumberObjects));
        }
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
