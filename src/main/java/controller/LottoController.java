package controller;

import domain.LottoGame;
import domain.Lottos;
import view.InputView;
import view.OutputView;

public class LottoController {

	private final LottoGame lottoGame;
	private final Lottos lottos;

	public LottoController() {
		this.lottoGame = new LottoGame(readLottoPurchaseAmount());
		this.lottos = new Lottos(lottoGame.getLottoCount());
	}

	private int readLottoPurchaseAmount() {
		return InputView.inputPurchaseAmount();
	}

	public void startGame() {
		showLottoPurchaseCount();
	}

	private void showLottoPurchaseCount() {
		OutputView.printLottoPurchaseCount(lottoGame.getLottoCount());
		showLottoNumbers();
	}

	private void showLottoNumbers() {
		OutputView.printLottos(lottos.getLottos());
		readLottoWinningNumber();
	}

	private void readLottoWinningNumber() {
		lottoGame.calculateGameResult(InputView.inputWinningNumber());
		showLottoGameResult();
	}

	private void showLottoGameResult() {
		OutputView.printLottoGameResult(lottoGame.getGameResult());
		OutputView.printLottoGameProfit(lottoGame.calculateGameProfit());
	}
}
