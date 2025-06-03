package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//로또 1장
public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto generateAutoLotto() {
        List<Integer> pickedNums = IntStream.rangeClosed(1, 45)
                                            .boxed()
                                            .collect(Collectors.toList());

        Collections.shuffle(pickedNums);

        List<LottoNumber> lottoNumbers = pickedNums.stream()
                                                    .limit(6)
                                                    .map(LottoNumber::new)
                                                    .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    public static Lotto generateManualLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                                                    .map(LottoNumber::new)
                                                    .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    public List<Integer> getNumberValues() {
        return numbers.stream()
                        .map(LottoNumber::getNumber)
                        .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
