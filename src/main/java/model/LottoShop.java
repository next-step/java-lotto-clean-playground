package model;

import util.NumberGenerator;

import java.util.List;
import java.util.stream.IntStream;

public class LottoShop {
    public static final int PRICE_PER_TICKET = 1000;
    private final LottoTickets tickets;

    public LottoShop(int amount, List<List<Integer>> manualNumbers, NumberGenerator generator) {
        int total = amount / PRICE_PER_TICKET;

        validateManualLotto(amount, total, manualNumbers);

        List<Lotto> manual = generateManualLotto(manualNumbers);
        List<Lotto> random = generateRandomLotto(total, manual, generator);

        this.tickets = new LottoTickets(new Lottos(manual), new Lottos(random));
    }

    public LottoShop(int amount, List<List<Integer>> manualNumbers) {
        this(amount, manualNumbers, new util.RandomNumberGenerator());
    }

    public LottoTickets getTickets() {
        return tickets;
    }

    private void validateManualLotto(int amount, int total, List<List<Integer>> manualNumbers) {
        validatePrice(amount);
        validateAmount(total, manualNumbers);
    }

    private void validatePrice(int price) {
        if (price < PRICE_PER_TICKET || price % PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException("구입 금액은 " + PRICE_PER_TICKET + "원 단위여야 합니다.");
        }
    }

    private void validateAmount(int total, List<List<Integer>> manualNumbers) {
        if (manualNumbers.size() > total) {
            throw new IllegalArgumentException("수동 로또 개수가 총 구매 개수를 초과합니다.");
        }
    }

    private List<Lotto> generateRandomLotto(int total, List<Lotto> manual, NumberGenerator generator) {
        return IntStream.range(0, total - manual.size())
                .mapToObj(i -> new Lotto(generator.generate()))
                .toList();
    }

    private List<Lotto> generateManualLotto(List<List<Integer>> manualNumbers) {
        return manualNumbers.stream()
                .map(Lotto::new)
                .toList();
    }
}
