package lotto.view;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.LottoMaker;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoPurchaseException;

public class LottoPurchaseView {
    private final LottoMaker lottoMaker = new LottoMaker();
    private final ViewInput input;
    private LottoPurchase purchase;

    public LottoPurchaseView(ViewInput input) {
        this.input = input;
    }

    public void purchaseLotto() {
        System.out.println("구입금액을 입력해 주세요.");

        int totalPrice = input.readTotalPrice();
        purchase = new LottoPurchase(totalPrice, lottoMaker);
        System.out.println();

        purchaseManualLotto();

        System.out.println();
        System.out.println(purchase.getLottoCount() + "개를 구매했습니다.");
    }

    private void purchaseManualLotto() {
        int count = readManualLottoCount();
        List<LottoNumbers> numbers = readManualNumbers(count);

        for (LottoNumbers number : numbers) {
            purchase.purchaseManually(number);
        }
    }

    private int readManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        int count = input.readManualLottoCount();
        if (count > purchase.getLottoCount()) {
            throw new LottoPurchaseException.IllegalCount("로또를 금액보다 많이 구매할 수 없습니다.");
        }

        System.out.println();
        return count;
    }

    private List<LottoNumbers> readManualNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LottoNumbers> numbers = IntStream.range(0, count)
                .mapToObj(i -> input.readLottoNumbers())
                .toList();

        System.out.println();
        return numbers;
    }

    public LottoPurchase getPurchase() {
        return purchase;
    }
}
