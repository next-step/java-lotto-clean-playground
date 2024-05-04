import domain.Lotto;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        Lotto lotto = new Lotto(new InputView().inputMoney());

        new OutputView().outputLottoTickets(lotto);
    }
}
