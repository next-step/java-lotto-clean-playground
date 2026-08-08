package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoGame {

    private final Lottos lottos;
    private CorrectLotto correctLotto;
    private final PurchasePrice purchasePrice;


    public LottoGame(PurchasePrice purchasePrice, List<String> manualLottoNumbers){
        this.purchasePrice = purchasePrice;
        this.lottos = new Lottos(createLottos(purchasePrice, manualLottoNumbers));
    }


    public Lottos getLottos() {
        return lottos;
    }


    public void createCorrectLotto(String[] values, int bonusBall) {
        correctLotto = new CorrectLotto(values, bonusBall);
    }

    private List<Lotto> createLottos(PurchasePrice purchasePrice, List<String> manualLottoNumbers) {

        List<Lotto> lottos = new ArrayList<>();

        int manualLottoCount = purchasePrice.getManualLottoCount();
        int autoLottoCount = purchasePrice.getAutoLottoCount();

        List<Lotto> autoLotto = AutoLotto.generateAutoLotto(autoLottoCount);
        List<Lotto> manualLotto = ManualLotto.generateManualLotto(manualLottoCount, manualLottoNumbers);

        lottos.addAll(manualLotto);
        lottos.addAll(autoLotto);

        return lottos;
    }


    public Map<Rank, Integer> getRanksCount() {
        if(correctLotto == null) {
            throw new IllegalStateException("correctLotto가 생성되지 않았습니다.");
        }
        return lottos.getRanksCount(correctLotto);
    }


    public float calculateProfit() {
        Map<Rank, Integer> ranksCount = getRanksCount();

        long sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrize() * ranksCount.get(rank);
        }
        return purchasePrice.calculateProfit(sum);
    }
}
