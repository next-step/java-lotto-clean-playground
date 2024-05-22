package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMarket {

    public List<Lotto> generateTickets(int price) {
        LottoTickets lottoTickets = new LottoTickets(price);
        int numberOfTickets = lottoTickets.getLottoTickets();
        List<Lotto> lotto = new ArrayList<>();

        generateLotto(numberOfTickets, lotto);

        return lotto;
    }

    private void generateLotto(int numberOfTickets, List<Lotto> lotto) {
        for (int count = 0; count < numberOfTickets; count++) {
            LottoNumbers lottoNumbers = new LottoNumbers();
            lotto.add(new Lotto(lottoNumbers.generateLottoNumber()));
        }
    }
}
