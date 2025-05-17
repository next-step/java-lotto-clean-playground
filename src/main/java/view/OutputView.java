package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoRank;
import domain.Lottos;
import java.util.EnumMap;
import java.util.Map;

public class OutputView {
    public void printPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printPurchasedLottos(Lottos lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers().getLottoNumbers().stream()
                    .map(LottoNumber::getNumber)
                    .toList()
            );
        }
    }

    public void printWinningLotto() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printLottoResult(Lottos lottos, Lotto winningLotto, int lottoPurchasePrice) {
        Map<LottoRank, Integer> lottoResultMap = new EnumMap<>(LottoRank.class);
        initLottoResultMap(lottoResultMap);
        calculateRank(lottos, winningLotto, lottoResultMap);

        System.out.println("\n당첨 통계\n---------");
        for (LottoRank rank : LottoRank.values()) {
            System.out.println(rank.getMessage() + " - " + lottoResultMap.get(rank) + "개");
        }
        System.out.printf("총 수익률은 %.2f입니다.", calculateROI(lottoResultMap, lottoPurchasePrice));
    }

    private void initLottoResultMap(Map<LottoRank, Integer> lottoResultMap) {
        for (LottoRank rank : LottoRank.values()) {
            lottoResultMap.put(rank, 0);
        }
    }

    private double calculateROI(Map<LottoRank, Integer> lottoResultMap, int lottoPurchasePrice) {
        long totalReturn = lottoResultMap.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();

        return (double) totalReturn / lottoPurchasePrice;
    }

    private void calculateRank(Lottos lottos, Lotto winningLotto, Map<LottoRank, Integer> lottoResultMap) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = lotto.getMatchCount(lotto, winningLotto);
            LottoRank.of(matchCount).ifPresent(rank ->
                    lottoResultMap.put(rank, lottoResultMap.get(rank) + 1)
            );
        }
    }
}
