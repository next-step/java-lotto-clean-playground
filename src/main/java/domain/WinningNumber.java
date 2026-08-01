package domain;

import java.util.*;

public class WinningNumber {
    public List<Integer> setWinningNumber(String enteredWinningNumber) {
        List<Integer> winningNumber = new ArrayList<>();
        String[] item = enteredWinningNumber.split(",");
        for (int i = 0; i < item.length; i++) {
            item[i] = item[i].trim();
            winningNumber.add(Integer.parseInt(item[i]));
        }
        Collections.sort(winningNumber);

        return winningNumber;
    }
}
