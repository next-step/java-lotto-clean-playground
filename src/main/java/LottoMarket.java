import java.util.*;
import java.util.stream.Collectors;

public class LottoMarket {
    private final List<Integer> winingNumbers;
    private final List<Lotto> lottos;

    public LottoMarket(List<Integer> winingNumbers) {
        validate(winingNumbers);
        this.winingNumbers = new ArrayList<>(winingNumbers); // 불변성 유지
        this.lottos = new ArrayList<>(); // 리스트 초기화
    }

    private void validate(List<Integer> winingNumbers) {
        if (winingNumbers.size() != 6) {
            throw new RuntimeException("로또 번호는 6개여야 합니다.");
        }
        if (winingNumbers.stream().anyMatch(n -> n < 1 || n > 45)) {
            throw new RuntimeException("로또 번호는 1이상 45이하여야 합니다.");
        }
    }

    public void randomLotto() {
        List<Integer> numbers = new Random().ints(1, 46)
                .distinct()
                .limit(6)
                .sorted()
                .boxed()
                .toList();
        Lotto lotto = new Lotto(numbers);
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public List<Integer> getWiningNumbers() {
        return Collections.unmodifiableList(winingNumbers);
    }

}
