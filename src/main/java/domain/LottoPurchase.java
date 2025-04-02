package domain;

import domain.generator.LottoGenerator;
import factory.LottoGeneratorFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoPurchase {

    private static final int PRICE_PER_LOTTO = 1000;

    private final List<Lotto> lottoList;

    public LottoPurchase(long purchaseAmount, List<Lotto> manualLottoList, LottoGeneratorFactory generatorFactory) {
        validateAmount(purchaseAmount);

        int totalCount = (int) purchaseAmount / PRICE_PER_LOTTO;
        int manualCount = manualLottoList.size(); //수동
        int autoCount = totalCount - manualCount; //자동

        LottoGenerator autoGenerator = generatorFactory.getAutoGenerator();
        List<Lotto> autoLottoList = autoGenerator.generateLottos(autoCount);

        this.lottoList = new ArrayList<>();
        this.lottoList.addAll(manualLottoList); // 수동 로또 추가
        this.lottoList.addAll(autoLottoList); // 자동 로또 추가
    }

    private void validateAmount(long purchaseAmount) {
        if (purchaseAmount < PRICE_PER_LOTTO) {
            throw new IllegalArgumentException("로또 최소 구매 금액은 1000원입니다.");
        }
        if (purchaseAmount % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException("로또 금액은 1000원 단위여야 합니다.");
        }
    }

    public List<Lotto> getLottoList() {
        return Collections.unmodifiableList(lottoList);
    }

    public int getLottoCount() {
        return lottoList.size();
    }
}
