import controller.LottoController;
import model.Lotto;
import model.RankCalculator;
import view.LottoInput;
import view.LottoOutput;

public class Main {
    public static void main(String[] args) {
        final var balance = LottoInput.inputBalance();
        final var lotto = new Lotto(balance);

        LottoController lottoController = new LottoController();
        lottoController.run(lotto);
    }
}
