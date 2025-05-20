package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoManage {
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_LIST_START_INDEX = 0;
    private static final int LOTTO_LIST_END_INDEX = 6;


    public static List<Integer> pullOutNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_START_NUMBER; i <= LOTTO_END_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public static void shuffleNumbers(List<Integer> numbers) {
        Collections.shuffle(numbers);
    }

    public static List<Integer> pickupLottoNumbers(List<Integer> numbers) {
        List<Integer> lottoNumbers = new ArrayList<>(numbers.subList(LOTTO_LIST_START_INDEX, LOTTO_LIST_END_INDEX));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
