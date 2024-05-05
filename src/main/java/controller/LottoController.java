package controller;

import domain.LottoGame;
import domain.Lottos;
import view.InputView;
import view.OutputView;

public class LottoController {

	private final LottoGame lottoGame;
	private final Lottos lottos;

	public LottoController() {
		this.lottoGame = new LottoGame(readPurchaseAmount());
		this.lottos = new Lottos(lottoGame.getLottoCount());
		showLottoPurchaseCount();
		showLottos();
	}

	private int readPurchaseAmount() {
		return InputView.inputPurchaseAmount();
	}

	private void showLottoPurchaseCount() {
		OutputView.printLottoPurchaseCount(lottoGame.getLottoCount());
	}

	private void showLottos() {
		OutputView.printLottos(lottos.getLottos());
	}

}
