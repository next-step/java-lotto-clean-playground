package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int MIN = 1;
    private static final int MAX = 45;

    public List<LottoTicket> generate(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(createTicket());
        }
        return tickets;
    }

    private LottoTicket createTicket() {
        List<Integer> pool = createPool();
        Collections.shuffle(pool);

        List<LottoNumber> picked = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            picked.add(LottoNumber.of(pool.get(i)));
        }

        return new LottoTicket(new Lotto(picked));
    }

    private List<Integer> createPool() {
        List<Integer> pool = new ArrayList<>();
        for (int i = MIN; i <= MAX; i++) {
            pool.add(i);
        }
        return pool;
    }
}
