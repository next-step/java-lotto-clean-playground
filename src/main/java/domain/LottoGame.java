package domain;

import java.util.List;
import java.util.Random;

public class LottoGame {
    private int lottoTotalPrice;
    private Lottos lottos;

    public LottoGame(int lottoTotalPrice, int manualCount, int autoCount, List<List<Integer>> manualNumbers) {
        this.lottoTotalPrice = lottoTotalPrice;
        int manualTicketPrice = manualCount * Lotto.PRICE_PER_TICKET;
        int remainingAmount = lottoTotalPrice - manualTicketPrice;
        this.lottos = new Lottos(calculateLottoAmount());
        addManualLottos(manualNumbers);//수동
        addAutoLottos(autoCount, remainingAmount);//자동
    }

    public int calculateLottoAmount() {
        return lottoTotalPrice / Lotto.PRICE_PER_TICKET;
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

    private void addManualLottos(List<List<Integer>> manualNumbers) {
        for (List<Integer> numbers : manualNumbers) {
            lottos.add(new Lotto(numbers));
        }
    }

    private void addAutoLottos(int autoCount, int remainingAmount) {
        Random random = new Random();
        for (int i = 0; i < autoCount && remainingAmount >= Lotto.PRICE_PER_TICKET; i++) {
            Lotto lotto = new Lotto();
            lottos.add(lotto);
            remainingAmount -= Lotto.PRICE_PER_TICKET;
        }
    }

}

