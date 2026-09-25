package view;

import domain.Lotto;
import domain.Lottos;
import dto.ResultDto;

import java.util.List;

public class OutputView {
    public void printLottos(Lottos lottos) {
        List<Lotto> lotto = lottos.getLottos();
        int count = lotto.size();
        System.out.println(count + "개를 구매했습니다.");

        for (int i = 0; i < count; i++) {
            printLotto(lotto.get(i));
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void printResult(ResultDto resultDto) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + resultDto.winning3() + "개");
        System.out.println("4개 일치 (50000원)- " + resultDto.winning4() + "개");
        System.out.println("5개 일치 (1500000원)- " + resultDto.winning5() + "개");
        System.out.println("6개 일치 (2000000000원)- " + resultDto.winning6() + "개");
        System.out.println("총 수익률은 " + resultDto.rateOfReturn() + "입니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
