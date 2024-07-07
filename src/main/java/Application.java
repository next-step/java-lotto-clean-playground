import domain.Lotto;
import domain.LottoList;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(final String... args) {
        final int numberOfLotto = InputView.LottoPurchased();
        LottoList lottoList = new LottoList();

        for (int i = 0; i < numberOfLotto; i++) {
            Lotto lotto = new Lotto();
            lotto.generateNumber();
            lottoList.addLotto(lotto);
        }

        OutputView.getLottoNumbers(lottoList);
    }
}
