package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LottoGenerator {
    private final List<Integer> basePool = createBasePool();

    public List<LottoTicket> generate(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(createTicket());
        }
        return tickets;
    }

    private LottoTicket createTicket() {
        List<Integer> pool = new ArrayList<>(basePool);
        Collections.shuffle(pool);

        List<LottoNumber> picked = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            picked.add(LottoNumber.of(pool.get(i)));
        }

        return new LottoTicket(new Lotto(picked));
    }

    private List<Integer> createBasePool() {
        List<Integer> pool = new ArrayList<>();
        for (int i = LottoNumber.min(); i <= LottoNumber.max(); i++) {
            pool.add(i);
        }
        return pool;
    }
}
