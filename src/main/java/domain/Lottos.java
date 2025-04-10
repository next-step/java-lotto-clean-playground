package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    public static int PRICE_PER_LOTTO = 1000;
    public static LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos buy(Money price, Lottos manualLottos) {
        List<Lotto> lottoList = new ArrayList<>(manualLottos.getLottos());
        validate(price, lottoList);

        Money autoPrice = price.sub((long) PRICE_PER_LOTTO * lottoList.size());
        for (int i = 0; i < autoPrice.divideBy(PRICE_PER_LOTTO); i++) {
            lottoList.add(Lotto.of(lottoNumberGenerator.generate()));
        }

        return new Lottos(lottoList);
    }

    private static void validate(Money price, List<Lotto> manualNumbers) {
        if (price.getPrice() % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException("구입 금액은 로또 가격(" + PRICE_PER_LOTTO + ")의 배수여야 합니다.");
        }

        long manualPrice = (long) PRICE_PER_LOTTO * manualNumbers.size();
        if (price.getPrice() < manualPrice) {
            throw new IllegalArgumentException("수동으로 구매할 로또 구입 금액이 총 구입 금액보다 큽니다.");
        }
    }

    public int getCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
