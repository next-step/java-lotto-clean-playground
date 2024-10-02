import domain.*;
import utils.WinningNumberFomatter;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 구입 금액 입력
        final int inputMoney = InputView.inputMoney();

        // 자동 로또 구매 후 출력
        LottoShop lottoShop = new LottoShop(inputMoney);
        Lottos lottos = lottoShop.saveLottos();
        OutputView.printLottos(lottos);

        // 당첨 번호 입력
        final String inputWinningNumbers = InputView.inputWinningNumbers();
        List<Integer> winNumbers = WinningNumberFomatter.formWinningNumbers(inputWinningNumbers);

        // 보너스 번호 입력
        final int inputBonus = InputView.inputBonus();
        final BonusNumber bonusNumber = new BonusNumber(inputBonus);

        // 결과 출력
        LottoResult lottoResult = new LottoResult();
        for(Lotto lotto : lottos.getLottos()){
            lottoResult.matchCountUp(lotto.matchingNumbers(winNumbers),bonusNumber.match(lotto));
        }
        OutputView.printWinningResult(lottoResult.getCounMap());
        OutputView.printProfitability(inputMoney);
    }
}
