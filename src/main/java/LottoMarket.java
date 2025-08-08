import java.util.*;

public class LottoMarket {
    private List<Integer> winningNumbers;
    private final List<Lotto> lottos;
    private final List<Lotto> manualLottos;

    public LottoMarket() {
        this.lottos = new ArrayList<>();
        this.manualLottos = new ArrayList<>();
    }

    public void setWinningNumbers(List<Integer> winningNumbers) {
        Lotto.validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    public void randomLotto() {
        List<Integer> numbers = new Random().ints(1, 46)
                .distinct()
                .limit(6)
                .sorted()
                .boxed()
                .toList();
        lottos.add(new Lotto(numbers));
    }

    public void manualLotto(List<Integer> manualLottoNums) {
        Lotto.validate(manualLottoNums);
        manualLottos.add(new Lotto(manualLottoNums));
    }

    public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(winningNumbers);
    }

    public List<Lotto> getManualLottos() {
        return Collections.unmodifiableList(manualLottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public List<Lotto> getAllLottos() {
        List<Lotto> allLottos = new ArrayList<>(manualLottos);
        allLottos.addAll(lottos);
        return Collections.unmodifiableList(allLottos);
    }

}
