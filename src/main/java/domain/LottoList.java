package domain;

import domain.generator.LottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoList {

    private static final int PRICE_PER_LOTTO = 1000;

    private final List<Lotto> lottoList;

    public LottoList(List<Lotto> lottos) {
        this.lottoList = new ArrayList<>(lottos);
    }

    public static LottoList create(long purchaseAmount, int manualLottoCount, LottoGenerator manualGenerator, LottoGenerator autoGenerator) {
        validateAmount(purchaseAmount);

        int totalLottoCount = (int) purchaseAmount / PRICE_PER_LOTTO;
        int autoLottoCount = totalLottoCount - manualLottoCount; //자동

        if (autoLottoCount < 0) {
            throw new IllegalArgumentException("수동 로또 개수가 구매 가능한 로또 개수를 초과했습니다.");
        }

        List<Lotto> manualLottoList = manualGenerator.generateLottos(manualLottoCount);
        List<Lotto> autoLottoList = autoGenerator.generateLottos(autoLottoCount);

        List<Lotto> allLottoList = new ArrayList<>();
        allLottoList.addAll(manualLottoList);
        allLottoList.addAll(autoLottoList);

        return new LottoList(allLottoList);
    }

    private static void validateAmount(long purchaseAmount) {
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
