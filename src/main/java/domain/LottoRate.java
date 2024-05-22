package domain;

import java.util.List;

public class LottoRate {

    private static final int INITIAL_NUMBER_ZERO = 0;
    private static final int THREE_MATCHES = 5000;
    private static final int FOUR_MATCHES = 50000;
    private static final int FIVE_MATCHES = 150000;
    private static final int SIX_MATCHES = 20000000;
    private int sum = 0;

    public List<Integer> matchesMoney() {
        return List.of(THREE_MATCHES, FOUR_MATCHES, FIVE_MATCHES, SIX_MATCHES);
    }

    public double LottoRate(int payMoney) {
        for (int i = INITIAL_NUMBER_ZERO; i < matchesMoney().size(); i++)
            sum += matchesMoney().get(i);
        return (double) sum / payMoney;
    }
}
