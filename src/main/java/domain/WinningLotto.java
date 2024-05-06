package domain;

import java.util.ArrayList;
import java.util.Arrays;

public class WinningLotto {
    public ArrayList<Integer> winningCount = new ArrayList<>(Arrays.asList(0, 0, 0, 0));

    private int checkLotto(ArrayList<Integer> lotto, ArrayList<Integer> winningNumbers) {
        int count = 0;

        for (int winningNumber : winningNumbers) {

            if (lotto.contains(winningNumber)) {
                count++;
            }
        }

        return count;
    }

    private void addWinningCount(int count) {

        if (count == 3) {
            winningCount.set(0, winningCount.get(0) + 1);
            return;
        }
        if (count == 4) {
            winningCount.set(1, winningCount.get(1) + 1);
            return;
        }
        if (count == 5) {
            winningCount.set(2, winningCount.get(2) + 1);
            return;
        }
        if (count == 6) {
            winningCount.set(3, winningCount.get(3) + 1);
        }
    }

    public void totalCheckLotto(ArrayList<ArrayList<Integer>> totalLottes, ArrayList<Integer> winningNumbers) {
        for (ArrayList<Integer> totalLotto : totalLottes) {
            addWinningCount(checkLotto(totalLotto, winningNumbers));
        }
    }

    public double calculateRate(int money) {
        return (double) (5000 * winningCount.get(0) + 50000 * winningCount.get(1) + 1500000 * winningCount.get(2) + 2000000000 * winningCount.get(3)) / money;
    }
}
