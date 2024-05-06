package view;

import model.CorrectNum;
import model.Lottos;
import model.OneLotto;
import model.RankCalculator;

import java.util.List;

public class Print {
    public static void printing(Lottos lottos, int manual, int automatic) {
        System.out.println("수동으로 " + manual + "장, 자동으로 " + automatic + "개를 구매했습니다.");
        List<OneLotto> oneLottoList = lottos.getMyLottos();
        for (OneLotto oneLotto : oneLottoList) {
            System.out.println(makeLottoStr(oneLotto));
        }
    }

    private static String makeLottoStr(OneLotto oneLotto) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");

        List<Integer> tmp = oneLotto.getLottoNumbers();

        for (Integer i : tmp) {
            sb.append(i.intValue());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public static void winning(RankCalculator rankCalculator, Lottos lottos) {
        long total = rankCalculator.calculateTotal(lottos);
        System.out.println("당첨 통계");
        System.out.println("---------");

        List<OneLotto> oneLottos = lottos.getMyLottos();
        List<CorrectNum> cor = CorrectNum.getList();
        for (int i = 3; i < cor.size(); i++) {
            int cnt = findCorrectNum(oneLottos, cor.get(i));
            System.out.println(makeWinningStr(cor.get(i), cnt));
        }
        System.out.println("총 수익률은 " + rankCalculator.rateOfReturn(lottos.getBalance(), total) + "입니다.");
    }

    private static String makeWinningStr(CorrectNum correctNum, int cnt) {
        StringBuffer sb = new StringBuffer();
        if (correctNum == CorrectNum.FIVE_NOT) {
            sb.append("5개 일치, 보너스 볼 일치(");
            sb.append(correctNum.getMoney());
            sb.append("원) -");
            sb.append(cnt);
            sb.append("개");
            return sb.toString();
        }
        sb.append(correctNum.getNum());
        sb.append("개 일치 (");
        sb.append(correctNum.getMoney());
        sb.append("원)- ");
        sb.append(cnt);
        sb.append("개");
        return sb.toString();
    }

    private static int findCorrectNum(List<OneLotto> oneLottos, CorrectNum correctNum) {
        int cnt = 0;
        for (OneLotto oneLotto : oneLottos) {
            if (correctNum == oneLotto.getCorrectCnt())
                cnt++;
        }
        return cnt;
    }
}
