package domain;

import java.util.*;

public class LottoParser {
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

    public List<Integer> parseSingleLotto(String input) {
        List<Integer> lottoNumbers = new ArrayList<>();
        String[] items = input.split(",");
        for (String item : items) {
            lottoNumbers.add(Integer.parseInt(item.trim()));
        }
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
