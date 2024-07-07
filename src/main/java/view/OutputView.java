package view;

import java.util.List;

import domain.Lotto;
import domain.LottoList;

public class OutputView {

    public static void getLottoNumbers(LottoList lottoList) {
        int numberOfLotto = lottoList.getNumberOfLotto();
        List<Lotto> lottos = lottoList.getLottoList();

        System.out.println(String.format("%s개를 구매했습니다.", numberOfLotto));
        for (int i = 0; i < numberOfLotto; i++) {
            System.out.println(lottos.get(i).getLottoNumber());
        }
    }
}
