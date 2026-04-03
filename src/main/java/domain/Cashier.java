package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cashier {
    public static final int THREE_CORRECT = 3;
    public static final int FOUR_CORRECT = 4;
    public static final int FIVE_CORRECT = 5;
    public static final int SIX_CORRECT = 6;
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

    public LottoResult getResults(Lotto lotto, LottoTicket winnerTicket) {
        List<Integer> results = lotto.getResults(winnerTicket);
        return new LottoResult(
                Collections.frequency(results, THREE_CORRECT),
                Collections.frequency(results, FOUR_CORRECT),
                Collections.frequency(results, FIVE_CORRECT),
                Collections.frequency(results, SIX_CORRECT)
        );
    }

    public Double getProfitRate(LottoResult result, int price) {
        int totalProfit = 5000 * result.getThreeCorrectCount()
                + 50000 * result.getFourCorrectCount()
                + 1500000 * result.getFiveCorrectCount()
                + 2000000000 * result.getSixCorrectCount();

        return (double) totalProfit / price;
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
