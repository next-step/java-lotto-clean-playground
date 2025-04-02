package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DrawResultsTest {

    private PurchaseAmount purchaseAmount;
    private Lottos autoLottos;
    private Lottos manualLottos;
    private DrawResults drawResults = new DrawResults();

    @BeforeEach
    void setUp() {
        int purchasePrice = 5000;
        int manualPurchaseAmount = 2;
        purchaseAmount = PurchaseAmount.of(purchasePrice, manualPurchaseAmount);
        autoLottos = Lottos.auto(purchaseAmount.getAutoPurchaseAmount(), new SixNumbersGenerator());
        List<Lotto> manualNumbersList = List.of(Lotto.from(List.of(1,2,3,4,5,6)), Lotto.from(List.of(1,2,3,4,5,6)));
        manualLottos = Lottos.manual(manualNumbersList);
    }

    @Test
    void 구매한_로또가_전부_낙첨되면_수익률은_손해이다() {
        Lotto winningNumbers = Lotto.from(List.of(7, 8, 9, 10, 11, 12));
        BonusBall bonusBall = BonusBall.of(13, winningNumbers);
        drawResults.calculateResults(manualLottos, autoLottos, winningNumbers, bonusBall);
        double profit = drawResults.calculateProfit(purchaseAmount.getTotalAmount());
        assertThat(profit).isLessThan(1);
    }

    @Test
    void 구매한_로또가_전부_당첨되면_수익률은_이익이다() {
        Lotto winningNumbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusBall bonusBall = BonusBall.of(7, winningNumbers);
        drawResults.calculateResults(manualLottos, autoLottos, winningNumbers, bonusBall);
        double profit = drawResults.calculateProfit(purchaseAmount.getTotalAmount());
        assertThat(profit).isGreaterThan(1);
    }
}
