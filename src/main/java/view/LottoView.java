package view;

import domain.*;
import enums.LottoRank;
import enums.LottoType;
import utils.LottoNumberFormatter;

import java.math.BigDecimal;
import java.util.*;

public class LottoView {

    final Scanner scanner = new Scanner(System.in);

    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요");
        return scanner.nextInt();
    }

    public int inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public void printLottosView(List<Lotto> lottos) {
        for (int i = 0; i < lottos.size(); i++) {
            printLotto(lottos.get(i));
        }
    }

    public List<Integer> inputWinningNumbersView() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return inputLotto();
    }

    public void printLotto(Lotto lotto) {
        printLottoType(lotto);
        Set<LottoNumber> lottoNumbers = lotto.getLottoNumbers();
        System.out.println(lottoNumbers);

    }

    public void printLottoType(Lotto lotto) {
        if (lotto.getType() == LottoType.MANUAL) {
            System.out.print("[수동] ");
        }
    }

    public void printLottoCount(Integer lottoCount) {
        System.out.println();
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public void printResultByRanks(Map<LottoRank, Integer> resultByRank) {
        System.out.println("당첨 통계");
        System.out.println("-------");
        for (LottoRank lottoRank : resultByRank.keySet()) {
            printSingleRankResult(resultByRank, lottoRank);
        }
    }

    private void printSingleRankResult(Map<LottoRank, Integer> resultByRank, LottoRank lottoRank) {

        if (lottoRank == LottoRank.MATCH_5_BONUS) {
            System.out.println(lottoRank.getMatchCount() + "개 일치, 보너스 볼 일치 (" + lottoRank.getPrize() + ") - " + resultByRank.get(lottoRank) + "개");
            return;
        }

        if (!(lottoRank == LottoRank.NO_MATCH)) {
            System.out.println(lottoRank.getMatchCount() + "개 일치 (" + lottoRank.getPrize() + ")- " + resultByRank.get(lottoRank));
        }
    }

    public void printProfitRate(BigDecimal profitRate) {
        System.out.println("총 수익률은 " + profitRate + "입니다.");
    }

    public List<List<Integer>> inputManualLottos(LottoCount lottoCount) {
        scanner.nextLine();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount.getLottoCount(); i++) {
            List<Integer> inputLotto = inputLotto();
            lottos.add(inputLotto);
        }
        return lottos;
    }

    private List<Integer> inputLotto() {
        String numbersString = scanner.nextLine();
        return LottoNumberFormatter.parse(numbersString);
    }

    public void printPurchaseInfo(Lottos lottos) {
        int autoLottoCount = lottos.getAutoCount().getLottoCount();
        int manualLottoCount = lottos.getManualCount().getLottoCount();
        System.out.println("수동으로 " + manualLottoCount + ", 자동으로 " + autoLottoCount + "를 구매했습니다.");
        printLottosView(lottos.getLottos());
    }

    public int inputBonusNumberView() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
