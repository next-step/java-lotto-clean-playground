package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoGenerator {
    private static final int LOTTO_NUMBERS_PER_TICKET = 6;

    private final Random random;

    public LottoGenerator() {
        this(new Random());
    }

    public LottoGenerator(Random random) {
        this.random = random;
    }

    public LottoTickets generateMultiple(int numberOfTickets) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            tickets.add(generateSingle());
        }
        return new LottoTickets(tickets);
    }

    public LottoTicket generateSingle() {
        List<Integer> pool = createNumberPool();
        Collections.shuffle(pool, random);
        List<Integer> picked = new ArrayList<>(pool.subList(0, LOTTO_NUMBERS_PER_TICKET));

        List<LottoNumber> numbers = new ArrayList<>();
        for (Integer number : picked) {
            numbers.add(LottoNumber.of(number));
        }
        Collections.sort(numbers);
        return new LottoTicket(numbers);
    }

    private List<Integer> createNumberPool() {
        List<Integer> pool = new ArrayList<>();
        for (int number = LottoNumber.MIN; number <= LottoNumber.MAX; number++) {
            pool.add(number);
        }
        return pool;
    }
}


