package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    private int lottoTicketCount;
    private List<Integer> lottoNumberList;
    private List<List<Integer>> lottoNumberTickets;

    public Lotto(int lottoTicketCount,
                 List<Integer> lottoNumberList,
                 List<List<Integer>> lottoNumberTickets) {
        this.lottoTicketCount = lottoTicketCount;
        this.lottoNumberList = lottoNumberList;
        this.lottoNumberTickets = lottoNumberTickets;
    }

    public int getLottoCount() {
        return this.lottoTicketCount;
    }

    public List<Integer> getLottoNumberList() {
        return this.lottoNumberList;
    }

    public List<List<Integer>> getLottoNumberTickets() {
        return this.lottoNumberTickets;
    }

}
