import domain.*;
import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.result.LottoResult;
import domain.result.LottoWinningChecker;
import domain.util.InputParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        Money money = InputParser.parseMoney(InputView.readBuyMoney()); //돈 입력

        //수동
        int manualLottoCount = InputParser.parseManualLottoCount(InputView.readManualLottoCount());
        List<String> manualLottoInputs = InputView.readManualLottos(manualLottoCount);
        Lottos manualLottos = InputParser.parseManualLottos(manualLottoInputs);

        //자동 로또 생성
        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);
        Lottos autoLottos = manager.purchaseLottos(money, manualLottoCount);

        // 수동 + 자동 합치기
        AllLottos allLottos = new AllLottos(manualLottos, autoLottos);

        //자동 로또 결과 출력
        OutputView.printLottoHistory(allLottos);

        Lotto winningNumbers = InputParser.parseLotto(InputView.readWinningNumber());  //당첨번호 입력
        LottoNumber bonusNumber = InputParser.parseBonusNumber(InputView.readBonusNumber()); //보너스 번호 입력
        WinningNumbers finalWinningNumbers  = new WinningNumbers(winningNumbers,bonusNumber);

        //당첨로직실행
        LottoResult result = LottoWinningChecker.checkLotto(allLottos.getAllLottos(), finalWinningNumbers);
        //당첨 결과 출력
        OutputView.printWinningResult(result, allLottos.size());
    }
}
