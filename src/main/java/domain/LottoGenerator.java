package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoGenerator {

    public List<LottoTicket> generate(int count) {
        return Stream.generate(this::createTicket)
                .limit(count)
                .toList();
    }

    private LottoTicket createTicket() {
        List<Integer> pool = createPool();
        Collections.shuffle(pool);
        List<Integer> picked = pool.subList(0, LottoNumbers.SIZE).stream().sorted().toList();
        return new LottoTicket(picked);
    }

    private List<Integer> createPool() {
        List<Integer> pool = new ArrayList<>();
        IntStream.rangeClosed(LottoNumbers.MIN, LottoNumbers.MAX).forEach(pool::add);
        return pool;
    }
}
