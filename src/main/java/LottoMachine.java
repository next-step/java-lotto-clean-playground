import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {

    public List<Lotto> generateLottos(int count) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(new Lotto(generateSingleLotto()));
        }
        return lottoTickets;
    }

    private List<LottoNumber> generateSingleLotto() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> selected = numbers.subList(0, 6);
        Collections.sort(selected);

        return selected.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
