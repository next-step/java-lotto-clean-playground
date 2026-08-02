package domain;

import java.util.Map;

public class LottoGame {

    private final Lottos lottos;
    private CorrectLotto correctLotto;
    private final PurchasePrice purchasePrice;

    public LottoGame(PurchasePrice purchasePrice){
        lottos = new Lottos(purchasePrice.getLottoNumberCount());
        this.purchasePrice = purchasePrice;
    }


    public Lottos getLottos() {
        return lottos;
    }

    public void createCorrectLotto(String[] values, LottoNumber bonusBall) {
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
