package domain;

import java.util.List;

public class LottoRate {

    private static final List<Integer> winningsMoney = List.of(5000, 50000, 1500000, 20000000);
    private int sum = 0;
    private static final int INITIAL_NUMBER_ZERO = 0;

    public double LottoRate(int payMoney) {
        for (int i = INITIAL_NUMBER_ZERO; i < winningsMoney.size(); i++)
            sum += winningsMoney.get(i);
        return (double) sum / payMoney;
    }
}
