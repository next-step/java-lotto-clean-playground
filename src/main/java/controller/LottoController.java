package controller;

import domain.LottoGame;
import domain.LottoGameResult;
import domain.Lottos;
import view.InputView;
import view.OutputView;

public class LottoController {

	private final LottoGame lottoGame;
	private final Lottos lottos;

	private LottoGameResult lottoGameResult;

	public LottoController() {
		this.lottoGame = new LottoGame(readLottoPurchaseAmount());
		this.lottos = new Lottos(lottoGame.getLottoCount());
	}

	public void startGame() {
		showLottoPurchaseCount();
		showLottoNumbers();
		showLottoGameResult();
	}

	private int readLottoPurchaseAmount() {
		return InputView.inputPurchaseAmount();
	}

	private void showLottoPurchaseCount() {
		OutputView.printLottoPurchaseCount(lottoGame.getLottoCount());
	}

	private void showLottoNumbers() {
		OutputView.printLottos(lottos.getLottos());
		readLottoWinningNumber();
	}

	private void readLottoWinningNumber() {
		this.lottoGameResult = new LottoGameResult(InputView.inputWinningNumber(), lottos);
	}

	private void showLottoGameResult() {
		OutputView.printLottoGameResult(lottoGameResult.getGameResult());
		OutputView.printLottoGameProfit(lottoGameResult.calculateGameProfit(lottoGame.getPurchaseAmount()));
	}
}
