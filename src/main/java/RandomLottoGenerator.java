import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLottoGenerator {


    public static final Random RANDOM = new Random();

    public Lotto generateLotto() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 6) {
            int randomNumber = RANDOM.nextInt(1, 45);
            numbers.add(randomNumber);
        }

        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }
}
