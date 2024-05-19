package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int LOTTO_PRICE = 1000;
    private static final int LIMIT_NUMBER = 45;
    private final int price;

    public Lotto(int price) {
        this.price = price;
    }

    private int getLottoTickets() {
        return price / LOTTO_PRICE;
    }

    private List<Integer> getLottoNumbers() {
        List<Integer> lotto = new ArrayList<>();
        for (int i = 0; i < LIMIT_NUMBER; i++) {
            lotto.add(i + 1);
        }
        Collections.shuffle(lotto);
        return lotto.subList(0, 6);
    }

    public void repeatLotto() {
        int lottoTickets = getLottoTickets();
        for (int i = 0; i < lottoTickets; i++) {
            getLottoNumbers();
        }
    }
}
