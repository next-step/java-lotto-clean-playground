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

    //외부에 리스트가 없을 때, 알아서 숫자뽑고 랜덤을 돌려서 생성->생성
    //과정을 숨김
    //근데 인자 없는 생성자를 만들어서 거기서 실행해주면 되는거 아닌가?
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

    @Override
    public String toString() {
        return numbers.toString();
    }
}
