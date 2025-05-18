import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {

    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;


    public List<Lotto> generateLottos(int count) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(new Lotto(generateSingleLotto()));
        }
        return lottoTickets;
    }

    private List<LottoNumber> generateSingleLotto() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_NUMBER_MIN; i <= LOTTO_NUMBER_MAX; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> selected = numbers.subList(0, LOTTO_NUMBER_COUNT);
        Collections.sort(selected);

        return selected.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
