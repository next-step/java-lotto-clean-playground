package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoGenerator {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBERS_PER_TICKET = 6;

    private final Random random;

    public LottoGenerator() {
        this(new Random());
    }

    public LottoGenerator(Random random) {
        this.random = random;
    }

    public List<List<Integer>> generateMultiple(int numberOfTickets) {
        List<List<Integer>> tickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            tickets.add(generateSingle());
        }
        return tickets;
    }

    public List<Integer> generateSingle() {
        List<Integer> pool = createNumberPool();
        Collections.shuffle(pool, random);
        List<Integer> ticket = new ArrayList<>(pool.subList(0, LOTTO_NUMBERS_PER_TICKET));
        Collections.sort(ticket);
        return ticket;
    }

    private List<Integer> createNumberPool() {
        List<Integer> pool = new ArrayList<>();
        for (int number = LOTTO_MIN_NUMBER; number <= LOTTO_MAX_NUMBER; number++) {
            pool.add(number);
        }
        return pool;
    }
}


