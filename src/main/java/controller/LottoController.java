package controller;

import java.util.List;

import domain.Lotto;
import domain.LottoGame;
import domain.LottoGameResult;
import domain.LottoNumber;
import domain.Lottos;
import view.InputView;
import view.OutputView;

public class LottoController {

	private LottoGame lottoGame;
	private Lottos lottos;

	private LottoGameResult lottoGameResult;

	public LottoController() {
	}

	public void startGame() {
		publishLotto();
		showLottoPurchaseCount();
		showLottoNumbers();
		calculateGameResult();
		showLottoGameResult();
	}

	private void publishLotto() {
		this.lottoGame = new LottoGame(readLottoPurchaseAmount());
		int manualLottoCount = InputView.inputManualLottoCount();
		this.lottos = new Lottos(manualLottoCount, lottoGame.getLottoCount(),
			InputView.inputManualLottoNumber(manualLottoCount));
	}

	private int readLottoPurchaseAmount() {
		return InputView.inputPurchaseAmount();
	}

	private void showLottoPurchaseCount() {
		OutputView.printLottoPurchaseCount(lottos.getManualLottoCount(), lottos.getAutoLottoCount());
	}

	private void showLottoNumbers() {
		OutputView.printLottos(lottos.getLottos());
	}

	private List<Integer> readLottoWinningNumber() {
		return InputView.inputWinningNumber();
	}

	private int readLottoBonusNumber() {
		return InputView.inputBonusNumber();
	}

	private void calculateGameResult() {
		Lotto winningLotto = new Lotto(readLottoWinningNumber());
		LottoNumber bonusNumber = new LottoNumber(readLottoBonusNumber());
		this.lottoGameResult = new LottoGameResult(winningLotto, bonusNumber);
		lottoGameResult.calculateGameResult(lottos);
	}

	private void showLottoGameResult() {
		OutputView.printLottoGameResult(lottoGameResult.getGameResult());
		OutputView.printLottoGameProfit(lottoGameResult.calculateGameProfit(lottoGame.getPurchaseAmount()));
	}

}
