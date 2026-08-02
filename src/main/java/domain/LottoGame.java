package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGame {

    private static final int MATCH_THREE_AMOUNT = 5000;
    private static final int MATCH_FOUR_AMOUNT = 50000;
    private static final int MATCH_FIVE_AMOUNT = 1500000;
    private static final int MATCH_SIX_AMOUNT = 2000000000;

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

    public void createCorrectLotto(String[] values) {
        correctLotto = new CorrectLotto(values);
    }


    private int threeMatchCount() {
        return Collections.frequency(lottos.correctCounts(correctLotto), 3);
    }

    private int fourMatchCount() {
        return Collections.frequency(lottos.correctCounts(correctLotto), 4);
    }

    private int fiveMatchCount() {
        return Collections.frequency(lottos.correctCounts(correctLotto), 5);
    }

    private int sixMatchCount() {
        return Collections.frequency(lottos.correctCounts(correctLotto), 6);
    }


    public List<Integer> correctCount() {
        List<Integer> correctCount = new ArrayList<>();
        correctCount.add(threeMatchCount());
        correctCount.add(fourMatchCount());
        correctCount.add(fiveMatchCount());
        correctCount.add(sixMatchCount());

        return correctCount;
    }

    public float calculateProfit() {
        int sum = 0;
        sum += threeMatchCount() * MATCH_THREE_AMOUNT;
        sum += fourMatchCount() * MATCH_FOUR_AMOUNT;
        sum += fiveMatchCount() * MATCH_FIVE_AMOUNT;
        sum += sixMatchCount() * MATCH_SIX_AMOUNT;
        return purchasePrice.calculateProfit(sum);
    }
}
