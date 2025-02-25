package controller;

import model.Lotto;
import dto.LottoDto;
import model.LottoPurchase;
import view.*;
import java.util.*;

public class LottoController {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoPurchase lottoPurchase = new LottoPurchase();

    public void run() {
        int purchaseAmount = lottoInputView.getPurchaseAmount();
        lottoInputView.printEmptyLine();

        List<Lotto> lottos = lottoPurchase.purchaseRandomLottos(purchaseAmount);
        lottoOutputView.printLottoAmount(lottos.size());

        printLottoList(lottos);
    }

    private void printLottoList(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            lottoOutputView.printLotto(LottoDto.from(lotto));
        }
    }

}
