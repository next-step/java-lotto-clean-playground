package view;

import model.Lotto;
import model.LottoNumber;
import model.RankCalculator;
import model.RankType;

import java.util.List;

public class LottoOutput {

    private static String makeLottoNumberToString(LottoNumber oneLotto) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");

        List<Integer> lottoNumbers = oneLotto.getNumbers();
        for (Integer lottoNumber : lottoNumbers) {
            buffer.append(lottoNumber.intValue());
            buffer.append(", ");
        }
        buffer.delete(buffer.length() - 2, buffer.length() - 1);
        buffer.append("]");
        return buffer.toString();
    }

    public static void printLottoNumbers(Lotto lotto) {
        System.out.println("수동으로 "
                + lotto.getManualCount() + "장,"
                + "자동으로 " + lotto.getAutoCount()
                + "개를 구매했습니다.");
        List<LottoNumber> lottoNumberList = lotto.getLottoNumberList();
        for (LottoNumber myLotto : lottoNumberList) {
            System.out.println(makeLottoNumberToString(myLotto));
        }
        System.out.println();
    }

    public static void printCorrectNumbers(RankCalculator rankCalculator) {
        List<RankType> rankTypeList = rankCalculator.getRankTypeList();
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (RankType rank : RankType.values()) {
            long count = rankTypeList.stream().filter(rankType -> rankType == rank).count();
            System.out.println(rank.getCorrectNum() + "개 일치 (" + rank.getPrize() + "원)- " + count + "개");
        }
    }

    public static void printRateOfReturn(double rate){
        System.out.printf("총 수익률은 %.2f 입니다.\n", rate);
    }
}
