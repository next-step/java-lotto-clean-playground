package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    private final int LOTTO_PRICE = 1000;
    private final int LAST_LOTTO_POSITION = 6;
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;

    private int lottoCount;
    private List<List<Integer>> lottoNumberTickets;

    public Lotto(int money) {
        this.lottoCount = (int) money / LOTTO_PRICE;
        lottoNumberTickets = new ArrayList<>();
        makeLottoNumberTickets(lottoCount);
    }

    public void makeLottoNumberTickets(int lottoCount) {
        for(int i = 0;i < lottoCount; i++) {
            lottoNumberTickets.add(generateLottoNumber());
        }
    }

    public List<Integer> generateLottoNumber() {
        Random random = new Random();

        List<Integer> lottoTicket = new ArrayList<>();

        while(lottoTicket.size() != LAST_LOTTO_POSITION) {
            lottoTicket.add(random.nextInt(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }

        Collections.sort(lottoTicket);

        return lottoTicket;
    }

    public int getLottoCount() {
        return this.lottoCount;
    }

    public List<List<Integer>> getLottoNumberTickets() {
        return this.lottoNumberTickets;
    }

}
