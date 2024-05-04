package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoService {
    private final int LOTTO_PRICE = 1000;
    private final int FIRST_LOTTO_POSITION = 0;
    private final int LAST_LOTTO_POSITION = 6;
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;

    public LottoService() {
    }

    public int makeLottoTicketCount(int money) {
        int lottoTicketCount = (int) money / LOTTO_PRICE;

        return lottoTicketCount;
    }

    public List<Integer> makeLottoNumberList() {
        List<Integer> lottoNumberList = new ArrayList<>();

        for(int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumberList.add(i);
        }

        return lottoNumberList;
    }

    public List<List<Integer>> makeLottoTickets(int lottoTicketCount,
                                                List<Integer> lottoNumberList) {

        List<List<Integer>> lottoNumberTickets = new ArrayList<>();

        List<Integer> lottoTicket;

        for(int i = 0;i < lottoTicketCount; i++) {
            Collections.shuffle(lottoNumberList);

            lottoTicket = lottoNumberList.subList(FIRST_LOTTO_POSITION, LAST_LOTTO_POSITION);
            Collections.sort(lottoTicket);
            lottoNumberTickets.add(lottoTicket.stream().toList());
        }

        return lottoNumberTickets;
    }
}
