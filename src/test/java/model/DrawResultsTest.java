package model;

import model.lotto.Lotto;
import model.lotto.ManualLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DrawResultsTest {

    private PurchaseAmount purchaseAmount;
    private DrawResults drawResults = new DrawResults();
    private Lottos lottos;

    @BeforeEach
    void setUp() {
        int purchasePrice = 5000;
        int manualPurchaseAmount = 1;
        purchaseAmount = PurchaseAmount.of(purchasePrice, manualPurchaseAmount);
        List<ManualLotto> manualLottos = List.of(ManualLotto.of(List.of(1, 2, 3, 4, 5, 6)));
        lottos = Lottos.of(manualLottos, purchaseAmount.getAutoPurchaseAmount(), new SixNumbersGenerator());
    }

    @Test
    void 구매한_로또가_전부_낙첨되면_수익률은_손해이다() {
        Lotto winningNumbers = ManualLotto.of(List.of(7, 8, 9, 10, 11, 12));
        LottoNumber bonusBall = LottoNumber.bonusNumber(13, winningNumbers);
        drawResults.calculateResults(lottos, winningNumbers, bonusBall);
        double profit = drawResults.calculateProfit(purchaseAmount.getTotalAmount());
        assertThat(profit).isLessThan(1);
    }

    @Test
    void 구매한_로또가_전부_당첨되면_수익률은_이익이다() {
        Lotto winningNumbers = ManualLotto.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = LottoNumber.bonusNumber(7, winningNumbers);
        drawResults.calculateResults(lottos, winningNumbers, bonusBall);
        double profit = drawResults.calculateProfit(purchaseAmount.getTotalAmount());
        assertThat(profit).isGreaterThan(1);
    }
}
