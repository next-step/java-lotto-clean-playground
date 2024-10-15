import domain.BonusNumber;
import domain.Lotto;
import domain.LottoResult;
import domain.LottoShop;
import domain.Lottos;
import utils.NumberFormatter;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 구입 금액 입력
        final int inputMoney = InputView.inputMoney();

        // 수동 로또 구매
        final int manualCount = InputView.inputManualCount();
        final Lottos ManualLottos = InputView.inputManualNumber(manualCount);

        // 자동 로또 구매 후 출력
        final LottoShop lottoShop = new LottoShop();
        final int autoCount = lottoShop.countAutoLottoTickets(inputMoney, manualCount);
        final Lottos lottos = lottoShop.saveLottos(autoCount, ManualLottos);
        OutputView.printLottos(lottos);

        // 당첨 번호 입력
        final String inputWinningNumbers = InputView.inputWinningNumbers();
        final List<Integer> winNumbers = NumberFormatter.formNumbers(inputWinningNumbers);

        // 보너스 번호 입력
        final int inputBonus = InputView.inputBonus();
        final BonusNumber bonusNumber = BonusNumber.inputBonusNumber(inputBonus);

        // 결과 출력
        final LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos.getLottos()) {
            lottoResult.matchCountUp(lotto.matchingNumbers(winNumbers), bonusNumber.match(lotto));
        }
        OutputView.printWinningResult(lottoResult.getCounMap());
        OutputView.printProfitability(lottoResult.calculateProfitability(inputMoney));
    }
}
