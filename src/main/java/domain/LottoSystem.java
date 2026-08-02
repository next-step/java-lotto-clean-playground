package domain;

import static domain.LottoRule.LOTTO_PRICE;

import java.util.List;
import java.util.stream.Stream;

public class LottoSystem {

  private final Lottos lottos;
  private final RandomLottoNumberGenerator randomLottoNumberGenerator;
  private final int purchasedLottoCount;

  public LottoSystem(int purchaseAmount, RandomLottoNumberGenerator randomLottoNumberGenerator) {
    validate(purchaseAmount, randomLottoNumberGenerator);
    this.randomLottoNumberGenerator = randomLottoNumberGenerator;
    this.purchasedLottoCount = purchaseAmount / LOTTO_PRICE;
    this.lottos = new Lottos(createLotto());
  }

  private void validate(int purchaseAmount, RandomLottoNumberGenerator randomLottoNumberGenerator) {
    if(purchaseAmount < LOTTO_PRICE){
      throw new IllegalArgumentException("로또 가격보다 입력한 값이 적습니다.");
    }
    if(randomLottoNumberGenerator == null){
      throw new IllegalArgumentException("정확한 인수를 입력해주세요");
    }
  }

  private List<Lotto> createLotto() {
    return Stream
        .generate(randomLottoNumberGenerator::generate)
        .map(Lotto::new)
        .limit(purchasedLottoCount)
        .toList();
  }
  public List<List<Integer>> purchasedLottoNumbers() {
    return lottos.purchasedLottoNumbers();
  }
  public Lottos purchasedLottos() {
    return lottos;
  }
  public int purchasedLottoCount() {
    return purchasedLottoCount;
  }
}
