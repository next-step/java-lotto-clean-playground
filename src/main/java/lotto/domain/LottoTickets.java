package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {// 로또 여러장을 관리
    private final List<Lotto> tickets; //이젠 Lotto로 구성된 리스트 사용

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = tickets;
    }

    public static LottoTickets generate(int count) {//로또를 count 수 만큼 자동 생성해서 LottoTickets으로 만들어줌
        List<Integer> allNumbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList()); //1부터 45까지 숫자를 리스트로 만드는 코드
        List<Lotto> tickets = IntStream.range(0, count)//0부터 count 전까지 반복
                .mapToObj(i -> { //각 반복마다 객체를 하나씩 만들겠다는 뜻
                    Collections.shuffle(allNumbers); //allNumbers 리스트 순서를 섞는다
                    return Lotto.from(allNumbers.subList(0, 6)); //섞은 리스트의 앞에 6개만 자름
                })
                .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }

    public Map<Rank, Long> matchAll(Lotto winningLotto) { //반환 타입은 Rank: 등수, Long : 등수 개수(Collectors.counting 메서드의 반환타입이 long으로 정해져있음)
        return tickets.stream()
                .map(ticket -> Rank.valueOf(ticket.countMatch(winningLotto))) //각 티켓이 당첨 번호와 몇개 맞는지 세서 그 개수를 가지고 Rank.valueOf(등수 결정)
                .collect(Collectors.groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), Collectors.counting()));
    } // 랭크 별로 묶고, Rank enum을 key로 쓰는 Map 하나를 만듦, 각 Rank안에 몇개의 요소가 있는지 셈

    public List<Lotto> getTickets() {
        return tickets;
    }

    public int size() {
        return tickets.size();
    } //티켓이 몇장 있는지 반환하는 메서드
}
