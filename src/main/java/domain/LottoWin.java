package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoWin {

    private static final String SPLIT_CHAR = ",";

    public List<Integer> calculateWinCounts(List<Lotto> lottoList, String inputNumber) {
        List<Integer> winNumber = splitString(inputNumber);
        return addWinCount(lottoList, winNumber);
    }

    private List<Integer> splitString(String inputNumber) {
        List<Integer> numbers = new ArrayList<>();
        String[] splitNumbers = inputNumber.split(SPLIT_CHAR);
        for (String number : splitNumbers) {
            numbers.add(Integer.parseInt(number));
        }
        return numbers;
    }

    private List<Integer> addWinCount(List<Lotto> lottoList, List<Integer> winNumber) {
        List<Integer> winCount = new ArrayList<>();
        for (Lotto lotto : lottoList) {
            winCount.add(checkWinNumber(lotto.getLottoNumbers(), winNumber));
        }
        return winCount;
    }

    private int checkWinNumber(List<Integer> actualNumber, List<Integer> winNumber) {
        int count = 0;
        for (int number : winNumber) {
            count = getCount(actualNumber, count, number);
        }
        return count;
    }

    private int getCount(List<Integer> actualNumber, int count, int number) {
        if (actualNumber.contains(number)) {
            count++;
        }
        return count;
    }
}
