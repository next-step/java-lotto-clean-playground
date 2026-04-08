package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets createCombined(List<Lotto> manualTickets, int autoCount) {
        //수동 로또 + 자동 로또를 합쳐서 전체 묶음을 만듦
        List<Lotto> total = new ArrayList<>(manualTickets); //total안에 먼저 수동로또 넣어두기
        LottoNumberStrategy randomStrategy = new RandomLottoNumberStrategy();

        for (int i = 0; i < autoCount; i++) {
            total.add(Lotto.from(randomStrategy)); //자동로또 생성
        }
        return new LottoTickets(total);
    }

    public Map<Rank, Long> matchAll(WinningLotto winningLotto) {
        return tickets.stream()
                .map(winningLotto::judge)
                //judge메서드 실행
                .collect(Collectors.groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), Collectors.counting()));
                //같은 등수끼리 묶어서 개수를 세라
    }

    public List<Lotto> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

}
