package domain;

import java.util.ArrayList;
import java.util.Arrays;

public class WinningLotto {
    public ArrayList<Integer> winningCount = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));

    private int checkLotto(ArrayList<Integer> lotto, ArrayList<Integer> winningNumbers) {
        int count = 0;

        for (int winningNumber : winningNumbers) {

            if (lotto.contains(winningNumber)) {
                count++;
            }
        }

        return count;
    }

    private void addWinningCount(ArrayList<Integer> totalLotto, int count, int bonusNumber) {

        if (count == 3) {
            winningCount.set(0, winningCount.get(0) + 1);
            return;
        }
        if (count == 4) {
            winningCount.set(1, winningCount.get(1) + 1);
            return;
        }
        if (count == 5) {
            addBonusCount(totalLotto, bonusNumber);
            return;
        }
        if (count == 6) {
            winningCount.set(4, winningCount.get(4) + 1);
        }
    }

    private void addBonusCount(ArrayList<Integer> lotto, int bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            winningCount.set(3, winningCount.get(3) + 1);
            return;
        }

        winningCount.set(2, winningCount.get(2) + 1);
    }

    public void totalCheckLotto(ArrayList<ArrayList<Integer>> totalLottes, ArrayList<Integer> winningNumbers, int bonusNumber) {
        for (ArrayList<Integer> totalLotto : totalLottes) {
            addWinningCount(totalLotto, checkLotto(totalLotto, winningNumbers), bonusNumber);
        }
    }

    public double calculateRate(int money) {
        return (double) (WinningNumbers.findByIndex(0) * winningCount.get(0) + WinningNumbers.findByIndex(1) * winningCount.get(1) + WinningNumbers.findByIndex(2) * winningCount.get(2) + WinningNumbers.findByIndex(3) * winningCount.get(3) + WinningNumbers.findByIndex(4) * winningCount.get(4)) / money;
    }
}

