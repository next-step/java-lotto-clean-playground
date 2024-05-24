package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMarket {

    private final int price;

    public LottoMarket(int price) {
        this.price = price;
    }

    public Lottos generateTickets() {
        LottoTicket lottoTicket = new LottoTicket(price);
        int numberOfTickets = lottoTicket.getLottoTickets();
        List<Lotto> lottoList = new ArrayList<>();

        generateLotto(numberOfTickets, lottoList);

        return new Lottos(lottoList);
    }

    private void generateLotto(int numberOfTickets, List<Lotto> lotto) {
        for (int count = 0; count < numberOfTickets; count++) {
            LottoNumbers lottoNumbers = new LottoNumbers();
            lotto.add(new Lotto(lottoNumbers.generateLottoNumber()));
        }
    }
}