import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLottoGenerator {

    public static final Random RANDOM = new Random();

    public Lotto generateLotto() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < Lotto.LOTTO_SIZE) {
            int randomNumber = RANDOM.nextInt(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE);
            numbers.add(randomNumber);
        }

        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }
}
