package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public int calculateNumberOfTickets(int amount) {
        return amount / LOTTO_PRICE;
    }

    public List<LottoTicket> generateLottoTickets(int numberOfTickets) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            LottoTicket ticket = generateSingleLottoTicket();
            tickets.add(ticket);
        }
        return tickets;
    }

    private LottoTicket generateSingleLottoTicket() {
        List<LottoNumber> numbersPool = createNumbersPool();
        Collections.shuffle(numbersPool);
        List<LottoNumber> selectedNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            selectedNumbers.add(numbersPool.get(i));
        }
        Collections.sort(selectedNumbers);
        return new LottoTicket(selectedNumbers);
    }

    public List<LottoNumber> createNumbersPool() {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int number = MIN_LOTTO_NUMBER; number <= MAX_LOTTO_NUMBER; number++) {
            numbers.add(new LottoNumber(number));
        }
        return numbers;
    }
}
