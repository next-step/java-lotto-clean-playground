package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final List<Integer> NUMBERS = createNumbers();

    private static List<Integer> createNumbers() {
        return IntStream.
                rangeClosed(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER)
                .boxed()
                .toList();
    }

    public static Lotto generateLotto() {
        List<Integer> numbers = new ArrayList<>(NUMBERS);
        List<Integer> lottoNumbers = generateLottoNumbers(numbers);
        return new Lotto(convertNumbersToLottoNumbers(lottoNumbers));
    }

    private static List<Integer> generateLottoNumbers(List<Integer> numbers) {
        Collections.shuffle(numbers);
        return selectLottoNumbers(numbers);
    }

    private static List<Integer> selectLottoNumbers(List<Integer> numbers) {
        List<Integer> lottoNumbers = new ArrayList<>(numbers.subList(0, Lotto.LOTTO_SIZE));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private static List<LottoNumber> convertNumbersToLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }
}
