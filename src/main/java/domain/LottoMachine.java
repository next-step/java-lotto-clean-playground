package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_NUMBER_START = 1;
    private static final int LOTTO_NUMBER_END = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public List<LottoTicket> generateTickets(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(generateSingleTicket());
        }
        return tickets;
    }

    private LottoTicket generateSingleTicket() {
        List<Integer> tempNumbers = generateNumberPool();
        Collections.shuffle(tempNumbers);
        List<Integer> selectedNumbers = pickLottoNumbers(tempNumbers);
        Collections.sort(selectedNumbers);
        return new LottoTicket(selectedNumbers);
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
