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
		final int manualLottoCount = readManualLottoCount();
		this.lottos = new Lottos(manualLottoCount, lottoGame.getLottoCount(),
			readManualLottoNumbers(manualLottoCount));
	}

	private void showLottoPurchaseCount() {
		OutputView.printLottoPurchaseCount(lottos.getManualLottoCount(), lottos.getAutoLottoCount());
	}

	private void showLottoNumbers() {
		OutputView.printLottos(lottos.getLottos());
	}

	private void calculateGameResult() {
		Lotto winningLotto = new Lotto(readLottoWinningNumber());
		LottoNumber bonusNumber = new LottoNumber(readLottoBonusNumber());
		this.lottoGameResult = new LottoGameResult(winningLotto, bonusNumber);
		lottoGameResult.calculateGameResult(lottos);
	}

	private void showLottoGameResult() {
		OutputView.printLottoGameResult(lottoGameResult.getGameResult());
		OutputView.printLottoGameProfit(lottoGameResult.calculateGameProfitRate(lottoGame.getPurchaseAmount()));
	}

	private int readLottoPurchaseAmount() {
		return InputView.inputPurchaseAmount();
	}

	private int readManualLottoCount() {
		return InputView.inputManualLottoCount();
	}

	private List<List<Integer>> readManualLottoNumbers(int count) {
		return InputView.inputManualLottoNumbers(count);
	}

	private List<Integer> readLottoWinningNumber() {
		return InputView.inputWinningNumber();
	}

	private int readLottoBonusNumber() {
		return InputView.inputBonusNumber();
	}

}
