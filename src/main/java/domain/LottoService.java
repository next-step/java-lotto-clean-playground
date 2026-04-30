package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    public static final int TICKET_PRICE = 1000;
    LottoGenerator lottoGenerator;

    public LottoService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<LottoTicket> buyTickets(int money, List<List<Integer>> manualNumbers) {
        validateMoney(money);
        validateManualCount(money, manualNumbers);

        List<LottoTicket> tickets = new ArrayList<>(generateManualTickets(manualNumbers));

        int autoCount = calculateAutoCount(money, manualNumbers.size());
        tickets.addAll(generateAutoTickets(autoCount));

        return tickets;
    }

    private List<LottoTicket> generateManualTickets(List<List<Integer>> manualNumbers) {
        List<LottoTicket> tickets = new ArrayList<>();

        for (List<Integer> numbers : manualNumbers) {
            tickets.add(new LottoTicket(
                    numbers.stream()
                            .map(LottoNumber::new)
                            .toList()
            ));
        }

        return tickets;
    }

    private List<LottoTicket> generateAutoTickets(int autoCount) {
        List<LottoTicket> tickets = new ArrayList<>();

        for (int i = 0; i < autoCount; i++) {
            tickets.add(new LottoTicket(lottoGenerator.generateNumbers()));
        }

        return tickets;
    }

    private int calculateAutoCount(int money, int manualCount) {
        return money / TICKET_PRICE - manualCount;
    }

    private void validateMoney(int money) {
        if (money < TICKET_PRICE) {
            throw new IllegalArgumentException("1000원 이상의 금액을 입력해주세요.");
        }

        if (money % TICKET_PRICE != 0) {
            throw new IllegalArgumentException("1000원 단위의 금액을 입력해주세요.");
        }
    }

    private void validateManualCount(int money, List<List<Integer>> manualNumbers) {
        if (manualNumbers == null) {
            throw new IllegalArgumentException("수동 번호는 비어 있을 수 없습니다.");
        }

        if (manualNumbers.size() > money / TICKET_PRICE) {
            throw new IllegalArgumentException("수동 구매 수량이 구입 금액을 초과할 수 없습니다.");
        }
    }
}
