package domain;

import java.util.List;
import java.util.Map;

public class LottoGame {

    private final Lottos lottos;
    private CorrectLotto correctLotto;
    private final PurchasePrice purchasePrice;


    public LottoGame(PurchasePrice purchasePrice, List<String> values){
        int autoLottoCount = purchasePrice.getAutoLottoCount();
        int manualLottoCount = purchasePrice.getManualLottoCount();
        lottos = new Lottos(autoLottoCount, manualLottoCount, values);
        this.purchasePrice = purchasePrice;
    }


    public Lottos getLottos() {
        return lottos;
    } // 로또 객체 반환

    public void createCorrectLotto(String[] values, LottoNumber bonusBall) { // 정답 로또 생성
        correctLotto = new CorrectLotto(values, bonusBall);
    }


    public Map<Rank, Integer> getRanksCount() { // 각 랭크의 개수 맵 반환
        return lottos.getRanksCount(correctLotto);
    }


    public float calculateProfit() { // 수익률 계산
        Map<Rank, Integer> ranksCount = getRanksCount();

        long sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrize() * ranksCount.get(rank);
        }
        return purchasePrice.calculateProfit(sum);
    }
}
