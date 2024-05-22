package domain;

import java.util.List;

public class WinningLotto {

    private static final int INITIAL_NUMBER = 0;

    private final List<Integer> winNumbers;

    public WinningLotto(List<Integer> winNumbers) {
        this.winNumbers = winNumbers;
    }

    public int getCount(List<Integer> ticketNumbers) {
        int count = INITIAL_NUMBER;
        for (int number : ticketNumbers) {
            count = numberOfWinnig(number, count);
        }
        return count;
    }

    private int numberOfWinnig(int number, int count) {
        if (winNumbers.contains(number)) {
            count++;
        }
        return count;
    }
}
