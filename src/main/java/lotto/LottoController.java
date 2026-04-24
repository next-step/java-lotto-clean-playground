package lotto;

import java.util.List;

public class LottoController {
    private final LottoInput lottoInput;
    private final LottoDisplay lottoDisplay;
    private final LottoPlay lottoPlay;
    private final LottoParser lottoParser;
    private final LottoMaker lottoMaker;

    public LottoController(LottoInput lottoInput, LottoDisplay lottoDisplay, LottoPlay lottoPlay) {
        this.lottoInput = lottoInput;
        this.lottoDisplay = lottoDisplay;
        this.lottoPlay = lottoPlay;
        this.lottoParser = new LottoParser();
        this.lottoMaker = new LottoMaker();
    }

    public void playLotto() {
        int price = getValidPrice();
        int maxLottoCount = price / 1000;

        int manualCount = getValidManualCount(maxLottoCount);
        List<Lotto> manualLottos = getManualLottos(manualCount);

        LottoPurchase purchase = new LottoPurchase(price, manualLottos, lottoMaker);
        int autoCount = purchase.getNumberOfLotto() - manualCount;

        lottoDisplay.displayPurchaseResult(manualCount, autoCount);

        LottoReceipt receipt = purchase.getReceipt();
        lottoDisplay.displayReceiptInfo(receipt, purchase.getChange());

        LottoDraw draw = lottoPlay.runDraw(receipt);

        lottoDisplay.displayResult(draw);
    }

    private int getValidPrice() {
        while (true) {
            try {
                int price = lottoInput.inputPrice();
                if (price < 1000) {
                    throw new IllegalArgumentException("1000원 이상 입력해야 합니다.");
                }
                return price;
            } catch (IllegalArgumentException e) {
                lottoDisplay.displayError(e.getMessage());
            }
        }
    }

    private void checkCountValidation(int manualCount, int maxCount) {
        if (manualCount > maxCount) {
            throw new IllegalArgumentException("구매 로또 수(" + maxCount + "장) 이하여야 합니다.");
        }
        if (manualCount < 0) {
            throw new IllegalArgumentException("0 이상의 숫자를 입력해 주세요.");
        }
    }

    private int getValidManualCount(int maxCount) {
        while (true) {
            try {
                int manualCount = lottoInput.inputManualCount();
                checkCountValidation(manualCount, maxCount);
                return manualCount;
            } catch (IllegalArgumentException e) {
                lottoDisplay.displayError(e.getMessage());
            }
        }
    }

    private List<Lotto> getManualLottos(int manualCount) {
        List<String> rawNumbers = lottoInput.inputManualNumbers(manualCount);
        return rawNumbers.stream()
                .map(lottoParser::parse)
                .toList();
    }
}
