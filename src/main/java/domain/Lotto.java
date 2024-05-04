package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    private final int LOTTO_PRICE = 1000;
    private final int FIRST_LOTTO_POSITION = 0;
    private final int LAST_LOTTO_POSITION = 6;
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;

    private int lottoCount;
    private List<Integer> lottoNumberList;
    private List<List<Integer>> lottoNumberTickets;

    public Lotto(int money) {
        this.lottoCount = (int) money / LOTTO_PRICE;
        makeLottoNumberList();
        makeLottoNumberTickets(lottoCount);
    }

    public void makeLottoNumberList() {
        lottoNumberList = new ArrayList<>();
        for(int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumberList.add(i);
        }
    }

    public void makeLottoNumberTickets(int lottoCount) {
        lottoNumberTickets = new ArrayList<>();

        List<Integer> lottoTicket;

        for(int i = 0;i < lottoCount; i++) {
            Collections.shuffle(lottoNumberList);

            lottoTicket = lottoNumberList.subList(FIRST_LOTTO_POSITION, LAST_LOTTO_POSITION);
            Collections.sort(lottoTicket);
            lottoNumberTickets.add(lottoTicket.stream().toList());
        }
    }

    public int getLottoCount() {
        return this.lottoCount;
    }

    public List<Integer> getLottoNumberList() {
        return this.lottoNumberList;
    }

    public List<List<Integer>> getLottoNumberTickets() {
        return this.lottoNumberTickets;
    }

}
