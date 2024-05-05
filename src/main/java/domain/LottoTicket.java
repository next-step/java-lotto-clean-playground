package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_NUMBER_MAX = 45;

    private List<Integer> numbers;

    public LottoTicket() {
        this.numbers = generateLottoNumbers();
    }

    private List<Integer> generateLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= LOTTO_NUMBER_MAX; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> selectedNumbers = numbers.subList(0, LOTTO_NUMBER_COUNT);
        Collections.sort(selectedNumbers); // 오름차순 정렬
        return selectedNumbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static int getLottoPrice() {
        return LOTTO_PRICE;
    }
}
