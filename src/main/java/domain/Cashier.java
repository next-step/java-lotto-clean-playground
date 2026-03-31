package domain;

import java.util.ArrayList;
import java.util.List;

public class Cashier {
    private final NumberListGenerator numberListGenerator;

    public Cashier(NumberListGenerator numberListGenerator) {
        this.numberListGenerator = numberListGenerator;
    }

    public Lotto generateTickets(int price) {
        validatePrice(price);
        int numberOfTickets = calculateNumberOfTickets(price);
        List<LottoTicket> generatedTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            generatedTickets.add(new LottoTicket(numberListGenerator.generate()));
        }

        return new Lotto(generatedTickets);
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        if (price < 1000) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
        if (price % 1000 != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력해주세요.");
        }
    }

    private int calculateNumberOfTickets(int price) {
        return price / 1000;
    }
}
