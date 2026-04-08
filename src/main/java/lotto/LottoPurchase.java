package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;
    private final int totalPrice;
    private final int change;
    private final int manualCount;

    public LottoPurchase(int totalPrice, List<Lotto> manualLottos, LottoMaker lottoMaker) {
        this.totalPrice = totalPrice;
        this.change = totalPrice % LOTTO_PRICE;
        this.manualCount = manualLottos.size();

        int totalTicketCount = totalPrice / LOTTO_PRICE;
        int autoCount = totalTicketCount - manualCount;

        // 방어 코드: 구입 금액보다 수동 개수가 많으면 예외 발생
        if (autoCount < 0) {
            throw new IllegalArgumentException("구입 금액보다 많은 수동 로또를 선택하셨습니다.");
        }

        // 수동 로또와 자동 로또를 합친 전체 리스트 생성
        List<Lotto> combinedLottos = new ArrayList<>(manualLottos);
        for (int i = 0; i < autoCount; i++) {
            combinedLottos.add(lottoMaker.makeLotto());
        }

        // Lottos 일급 컬렉션에 전체 리스트 전달
        this.lottos = new Lottos(combinedLottos);
    }

    public LottoReceipt printReceipt() {
        return new LottoReceipt(lottos, totalPrice);
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return getNumberOfLotto() - manualCount;
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }

    public int getChange() {
        return change;
    }
}
