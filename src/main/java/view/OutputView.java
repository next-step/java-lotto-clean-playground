package view;

import domain.BingoResult;
import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    public static void printLottos(int manualLottoNum, int automaticLottoNum, Lottos lottos) {
        System.out.println("수동으로 " + manualLottoNum + "장, 자동으로 " + automaticLottoNum + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto.getLotto());
        }
        System.out.println();
    }

    public static void printLotto(List<LottoNumber> lotto) {
        List<Integer> lottoList = new ArrayList<>();
        for (LottoNumber lottoNumber : lotto) {
            lottoList.add(lottoNumber.getLottoNumber());
        }
        System.out.println(lottoList);
    }


    public static void printManualLottoMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printResult() {
        System.out.println("당첨 통계\n---------");
        for (BingoResult.Result result : BingoResult.Result.values()) {
            System.out.println(result.getMessage() + result.getMatchNum() + "개");
        }
    }

    public static void printPercent(double profitPercent) {
        System.out.println("총 수익률은 " + profitPercent + "%입니다.");
    }
}
