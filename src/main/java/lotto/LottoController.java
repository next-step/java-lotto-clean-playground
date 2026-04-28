package lotto;

import java.util.List;

public class LottoController {
    private final LottoInput lottoInput;
    private final LottoDisplay lottoDisplay;
    private final LottoParser lottoParser;
    private final LottoMaker lottoMaker;

    public LottoController(LottoInput lottoInput, LottoDisplay lottoDisplay) {
        this.lottoInput = lottoInput;
        this.lottoDisplay = lottoDisplay;
        this.lottoParser = new LottoParser();
        this.lottoMaker = new LottoMaker();
    }

    public void playLotto() {
        LottoPrice price = getPrice();
        int maxLottoCount = price.getMaxLottoCount();

        int manualCount = getValidManualCount(maxLottoCount);
        List<Lotto> manualLottos = lottoInput.getManualLottos(manualCount);

        LottoPurchase purchase = new LottoPurchase(price, manualLottos, lottoMaker);
        int autoCount = purchase.getAutoCount();

        lottoDisplay.displayPurchaseResult(manualCount, autoCount);

        LottoReceipt receipt = purchase.getReceipt();
        lottoDisplay.displayReceiptInfo(receipt, purchase.getChange());

        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getValidBonus(winningLotto);

        LottoDraw draw = new LottoDraw(winningLotto, bonusNumber, receipt);
        lottoDisplay.displayResult(draw);
    }

    private LottoPrice getPrice() {
        while (true) {
            try {
                return lottoInput.inputPrice();
            } catch (IllegalArgumentException e) {
                lottoInput.showError(e.getMessage());
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
                lottoInput.showError(e.getMessage());
            }
        }
    }

    private Lotto getWinningLotto() {
        while (true) {
            try {
                String rawNumbers = lottoInput.inputWinningNumbers();
                return lottoParser.parse(rawNumbers);
            } catch (IllegalArgumentException e) {
                lottoInput.showError(e.getMessage());
            }
        }
    }

    private LottoNumber getValidBonus(Lotto winningLotto) {
        while (true) {
            try {
                int bonusValue = lottoInput.inputBonusNumber();
                LottoNumber bonus = new LottoNumber(bonusValue);
                checkSameBonusNumber(winningLotto, bonus);
                return bonus;
            } catch (IllegalArgumentException e) {
                lottoInput.showError(e.getMessage());
            }
        }
    }

    private void checkSameBonusNumber(Lotto winningLotto, LottoNumber bonus) {
        if (winningLotto.numbers().contains(bonus)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 같을 수 없습니다.");
        }
    }
}
