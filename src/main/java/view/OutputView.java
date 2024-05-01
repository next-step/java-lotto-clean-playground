package view;

import domain.Lotto;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        List<Integer> lottoNumber = lotto.getLottoNumber();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Integer number : lottoNumber) {
            stringJoiner.add(number.toString());
        }
        String lottoNumbers = stringJoiner.toString();
        System.out.println("[" + lottoNumbers + "]");
    }
}
