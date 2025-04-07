package controller;

import domain.BonusNumber;
import domain.Lotto;
import domain.LottoNumberGenerator;
import domain.LottoPrice;
import domain.Lottos;
import domain.Statistics;
import domain.WinningNumbers;
import java.util.List;
import view.InputView;
import view.ResultView;

public class LottoController {

  private final InputView inputView;
  private final ResultView resultView;
  private final LottoNumberGenerator lottoNumberGenerator;

  public LottoController(InputView inputView, ResultView resultView, LottoNumberGenerator lottoNumberGenerator) {
    this.inputView = inputView;
    this.resultView = resultView;
    this.lottoNumberGenerator = lottoNumberGenerator;
  }

  public void run() {
    // 구매 금액 처리
    long money = inputView.getPurchaseMoney();
    LottoPrice purchaseMoney=new LottoPrice(money);

    // 수동 구매 수 입력
    int manualTicketCount = inputView.getManualTicketCount();

    // 수동 티켓 번호 입력
    List<Lotto> manualLottos = inputView.getManualLottoTickets(manualTicketCount);

    // 로또 생성 및 출력
    Lottos lottos = Lottos.from(purchaseMoney, manualLottos, manualTicketCount, lottoNumberGenerator);
    resultView.printLottos(lottos);

    // 당첨 번호 입력
    WinningNumbers winningNumbers = inputView.getWinningNumbers();

    // 보너스 번호 입력
    BonusNumber bonusNumber = new BonusNumber(inputView.getBonusNumber(), winningNumbers);

    // 당첨 결과 출력
    Statistics statistics = new Statistics(lottos, winningNumbers, lottos.getPurchaseMoney(), bonusNumber);
    resultView.printStatistics(statistics);
  }
}
