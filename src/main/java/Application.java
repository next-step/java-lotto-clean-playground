import domain.*;
import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.result.LottoResult;
import domain.result.LottoWinningChecker;
import domain.util.InputParser;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {

        int money = InputView.readBuyMoney(); //돈 입력

        //자동 로또 생성
        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);
        Lottos lottos = manager.purchaseLottos(money);

        //자동 로또 결과 출력
        OutputView.printLottoHistory(lottos);

        Lotto winningNumbers = InputParser.parseLotto(InputView.readWinningNumber());  //당첨번호 입력
        LottoNumber bonusNumber = InputParser.parseBonusNumber(InputView.readBonusNumber()); //보너스 번호 입력
        WinningNumbers finalWinningNumbers  = new WinningNumbers(winningNumbers,bonusNumber);

        //당첨로직실행
        LottoResult result = LottoWinningChecker.checkLotto(lottos, finalWinningNumbers);
        //당첨 결과 출력
        OutputView.printWinningResult(result, lottos.size());
    }
}
