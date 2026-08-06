package domain;

import view.InputView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PurchaseManage{
    public static final int LOTTO_PRICE = 1000;
    private final int totalCount;
    private final int manualCount;
    private int bonusBall;
    private Lotto winningLotto;

    public PurchaseManage(int price, int manualCount) {
        this.totalCount = price / LOTTO_PRICE;
        validateManualCount(manualCount);
        this.manualCount = manualCount;
    }

    LottoParser lottoParser = new LottoParser();

    private void validateManualCount(int manualCount) {
        if (totalCount < manualCount || manualCount < 0) {
            System.out.println("0개 이상" + " " + totalCount + "개 이하로 입력해주세요");
            throw new IllegalArgumentException("수동 구매 수량은 0개 이상 " + totalCount + "개 이하이어야 합니다.");
        }
    }

    public Lottos buyLottos(int manualCount) {
        int automaticLottoCount = totalCount - manualCount;

        List<Lotto> manualLottos = makeManualLotto(manualCount);

        List<Lotto> purchaseLottos = new ArrayList<>(manualLottos);

        for (int i = 0; i < automaticLottoCount; i++) {
            purchaseLottos.add(new Lotto());
        }
        return new Lottos(purchaseLottos);
    }

    public List<Lotto> makeManualLotto(int manualCount) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            String input = InputView.getManualPurchasedLottos();
            List<Integer> numbers = lottoParser.parseInput(input);
            manualLottos.add(new Lotto(numbers));
        }
        return manualLottos;
    }

    public void setLottoResult() {
        setWinningNumber();
        setBonusBall();
    }

    private void setWinningNumber() {
        String enteredWinningNumber = InputView.getWinningNumber();
        List<Integer> winningNumber =  lottoParser.parseInput(enteredWinningNumber);
        this.winningLotto = new Lotto(winningNumber);
    }

    private void setBonusBall() {
        this.bonusBall = validateBonusBall(InputView.getBonusNumber());
    }

    private int validateBonusBall(int bonusBall) {
        if (bonusBall < 1 || bonusBall > 45) {
            throw new IllegalArgumentException("보너스 볼은 1부터 45 사이의 숫자여야 합니다.");
        }
        List<Integer> copiedWinningNumber = winningLotto.getLottoNumbers();
        Set<Integer> uniqueBonusball = new HashSet<>(copiedWinningNumber);
        if (!uniqueBonusball.add(bonusBall)) {
            throw new IllegalArgumentException("중복된 로또 번호가 존재합니다.");
        }
        return bonusBall;
    }


    public Lotto getWinningNumber() {
        return winningLotto;
    }

    public int getBonusBall() {
        return bonusBall;
    }
}
