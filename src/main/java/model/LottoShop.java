package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoShop {
    public static final int PRICE_PER_TICKET = 1000;
    private final LottoTickets tickets;

    public LottoShop(int amount, List<List<Integer>> manualNumbers) {
        int total = amount / PRICE_PER_TICKET;

        validateManualLotto(amount, total, manualNumbers);
        List<Lotto> manual = manualNumbers.stream()
                .map(Lotto::new)
                .toList();

        int autoCount = total - manual.size();

        List<Lotto> random = IntStream.
                range(0, autoCount).
                mapToObj(i -> createRandomLotto())
                .toList();

        this.tickets = new LottoTickets(random, manual);
    }

    private Lotto createRandomLotto() {
        List<Integer> random = new java.util.ArrayList<>
                (IntStream.rangeClosed(Lotto.MIN_LOTTO_NUMBER, Lotto.MAX_LOTTO_NUMBER)
                .boxed()
                .toList());
        Collections.shuffle(random);
        return new Lotto(random.subList(0, Lotto.LOTTO_SIZE));
    }

    public LottoTickets getTickets() {
        return tickets;
    }

    public void validateManualLotto(int amount, int total, List<List<Integer>> manualNumbers) {
        if (amount < PRICE_PER_TICKET || amount % PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException("구입 금액은 " + PRICE_PER_TICKET + "원 단위여야 합니다.");
        }
        if (manualNumbers.size() > total) {
            throw new IllegalArgumentException("수동 로또 개수가 총 구매 개수를 초과합니다.");
        }
    }
}
