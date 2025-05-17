import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {

    public List<List<Integer>> generateLottos(int amount) {
        int count = amount / 1000;
        List<List<Integer>> lottoTickets = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoTickets.add(generateSingleLotto());
        }

        return lottoTickets;
    }

    private List<Integer> generateSingleLotto() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> lotto = numbers.subList(0, 6);
        Collections.sort(lotto);
        return new ArrayList<>(lotto);
    }
}
