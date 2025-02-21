package controller;

import domain.Lotto;
import dto.LottoDto;
import service.LottoService;
import view.*;
import java.util.*;

public class LottoController {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoService lottoService = new LottoService();

    public void run() {
        int purchaseAmount = getPurchaseAmount();
        int lottoAmount = lottoService.calculateLottoAmount(purchaseAmount);
        lottoInputView.printEmptyLine();

        lottoOutputView.printLottoAmount(lottoAmount);

        List<Lotto> lottoList = createLottoList(lottoAmount);

        printLottoList(lottoList);
    }

    private int getPurchaseAmount() {
        int purchaseAmount = lottoInputView.getPurchaseAmount();
        lottoService.validatePurchaseAmount(purchaseAmount);

        return purchaseAmount;
    }

    private List<Lotto> createLottoList(int lottoAmount) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < lottoAmount; i++) {
            lottoList.add(getLotto());
        }

        return Collections.unmodifiableList(lottoList);
    }

    private Lotto getLotto() {
        return Lotto.getRandomLotto();
    }

    private void printLottoList(List<Lotto> lottoList) {
        for (Lotto lotto : lottoList) {
            lottoOutputView.printLotto(LottoDto.from(lotto));
        }
    }

}
