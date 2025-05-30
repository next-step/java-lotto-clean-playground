package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//로또 1장
public class Lotto {

    private final List<LottoNumber> numbers;

    //외부에 이미 list를 갖고 있을 때
    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto generateLotto() {
        List<Integer> pickedNums = IntStream.range(1, 46)
                                           .boxed()
                                           .collect(Collectors.toList());

        Collections.shuffle(pickedNums);

        List<LottoNumber> lottoNumbers = pickedNums.stream()
                                                 .limit(6)
                                                 .map(LottoNumber::new)
                                                 .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
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
