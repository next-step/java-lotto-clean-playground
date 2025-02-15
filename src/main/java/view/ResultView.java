package view;

import java.util.List;

import domain.Lotto;

public class ResultView {

    public static void outputResult(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(System.out::println);
    }
}
