package domain;

import view.InputView;

import java.util.ArrayList;
import java.util.List;

public class PurchaseManage{
    public static final int LOTTO_PRICE = 1000;
    private int bonusBall;
    private List<Integer> winningNumber;

    LottoParser lottoParser = new LottoParser();

    public Lottos buyLottos(int price, int manualCount) {
        int totalCount = price / LOTTO_PRICE;
        int automaticLottoCount = totalCount - manualCount;

        List<LottoNumber> manualLottos = makeManualLotto(manualCount);
        List<LottoNumber> purchaseLottos = new ArrayList<>(manualLottos);

        for (int i = 0; i < automaticLottoCount; i++) {
            purchaseLottos.add(new LottoNumber());
        }
        return new Lottos(purchaseLottos);
    }

    public List<LottoNumber> makeManualLotto(int manualCount) {
        List<LottoNumber> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            String input = InputView.getManualPurchasedLottos();
            List<Integer> numbers = lottoParser.parseInput(input);
            manualLottos.add(new LottoNumber(numbers));
        }
        return manualLottos;
    }

    public void setLottoResult() {
        setWinningNumber();
        setBonusBall();
    }

    private void setWinningNumber() {
        String enteredWinningNumber = InputView.getWinningNumber();
        this.winningNumber =  lottoParser.parseInput(enteredWinningNumber);
    }

    private void setBonusBall() {
        this.bonusBall = InputView.getBonusNumber();
    }


    public List<Integer> getWinningNumber() {
        return winningNumber;
    }

    public int getBonusBall() {
        return bonusBall;
    }
}
