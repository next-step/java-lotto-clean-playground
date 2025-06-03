package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_NUMBER_START = 1;
    private static final int LOTTO_NUMBER_END = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public List<Lotto> generateTickets(List<Lotto> handTickets, int autoCount) {
        List<Lotto> tickets = new ArrayList<>(handTickets);
        for (int i = 0; i < autoCount; i++) {
            tickets.add(generateRandomSingleTicket());
        }
        return tickets;
    }

    private Lotto generateRandomSingleTicket() {
        List<Integer> tempNumbers = generateNumberPool();
        Collections.shuffle(tempNumbers);
        List<Integer> selectedNumbers = pickLottoNumbers(tempNumbers);
        Collections.sort(selectedNumbers);
        return new Lotto(selectedNumbers);
    }

    private List<Integer> generateNumberPool() {
        List<Integer> numbers = new ArrayList<>();
        for (int number = LOTTO_NUMBER_START; number <= LOTTO_NUMBER_END; number++) {
            numbers.add(number);
        }
        return numbers;
    }

    private List<Integer> pickLottoNumbers(List<Integer> pool) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            result.add(pool.get(i));
        }
        return result;
    }
}
