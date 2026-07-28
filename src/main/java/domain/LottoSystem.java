package domain;

import static domain.LottoRule.LOTTO_PRICE;
import generator.LottoNumberGenerator;
import java.util.List;
import java.util.stream.Stream;

public class LottoSystem {

    private final Integer purchaseAmount;
    private final Lottos lottos;
    private final LottoNumberGenerator lottoNumberGenerator;
    private final Integer purchasedLottoCount;

    public LottoSystem(Integer purchaseAmount, LottoNumberGenerator lottoNumberGenerator) {
        validate(purchaseAmount, lottoNumberGenerator);
        this.purchaseAmount = purchaseAmount;
        this.lottoNumberGenerator = lottoNumberGenerator;
        this.purchasedLottoCount = purchaseAmount / LOTTO_PRICE;
        this.lottos = new Lottos(createLotto());
    }

    private void validate(Integer purchaseAmount, LottoNumberGenerator lottoNumberGenerator){
        if(purchaseAmount < LOTTO_PRICE){
            throw new IllegalArgumentException("로또 가격보다 입력한 값이 적습니다.");
        }
    }

    private List<Lotto> createLotto(){
        return Stream
            .generate(lottoNumberGenerator::generate)
            .map(Lotto::new)
            .limit(purchasedLottoCount)
            .toList();
    }
    public List<List<Integer>> purchasedLottoNumbers() {
        return lottos.purchasedLottoNumbers();
    }
    public Lottos purchasedLottos(){
        return lottos;
    }
    public Integer purchasedLottoCount() {
        return purchasedLottoCount;
    }
}
