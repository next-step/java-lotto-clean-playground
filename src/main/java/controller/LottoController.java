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

        lottoService.validatePurchaseAmount(purchaseAmount);
        int lottoAmount = lottoService.calculateGetLottoAmount(purchaseAmount);
        lottoInputView.printEmptyLine();

        List<Lotto> lottoList = createLottoList(lottoAmount);
        lottoOutputView.printLottoAmount(lottoAmount);

        for (Lotto lotto : lottoList) {
            lottoOutputView.printLotto(LottoDto.from(lotto));
        }
    }

    private List<Lotto> createLottoList(int lottoAmount) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < lottoAmount; i++) {
            lottoList.add(getLotto());
        }

        return List.copyOf(lottoList);
    }

    private int getPurchaseAmount() {
        return lottoInputView.getPurchaseAmount();
    }

    private Lotto getLotto() {
        return Lotto.getRandomLotto();
    }

}
