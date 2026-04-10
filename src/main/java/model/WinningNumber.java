package model;

import java.util.List;

public class WinningNumber {
    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 45;

    private List<Integer> winningNumber;

    public WinningNumber(List<Integer> winningNumber) {
        this.winningNumber = winningNumber;
        validateRange(winningNumber);
    }


    private void validateRange(List<Integer> Numbers) {
        for (Integer number : Numbers) {
            if (MIN_NUMBER > number || number > MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR]1과 45사이의 숫자를 입력하시오");
            }
        }
    }

    public List<Integer> getWinningNumber() {
        return winningNumber;
    }
}
