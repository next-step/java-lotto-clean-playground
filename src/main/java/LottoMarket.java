import java.util.*;

public class LottoMarket {
    private List<Integer> winningNumbers;
    private final List<Lotto> lottos;
    private final List<Lotto> manualLottos;

    public LottoMarket() {
        this.lottos = new ArrayList<>();
        this.manualLottos = new ArrayList<>();
    }

    public void setWinningNumbers(List<Integer> winningNumbers){
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new RuntimeException("로또 번호는 6개여야 합니다.");
        }
        if (numbers.stream().anyMatch(n -> n < 1 || n > 45)) {
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
        lottos.add(new Lotto(numbers));
    }

    public void manualLotto(List<Integer> manualLottoNums){
        validate(manualLottoNums); // 유효성 검사 후 추가
        manualLottos.add(new Lotto(manualLottoNums));
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public List<Lotto> getManualLottos() {
        return Collections.unmodifiableList(manualLottos);
    }

    public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(winningNumbers);
    }

    // 자동 + 수동 로또 리스트를 반환하는 메서드 추가
    public List<Lotto> getAllLottos() {
        List<Lotto> allLottos = new ArrayList<>(manualLottos);
        allLottos.addAll(lottos);
        return Collections.unmodifiableList(allLottos);
    }
}
