import controller.LottoController;
import model.Lotto;
import view.LottoInput;

public class Main {
    public static void main(String[] args) {
        final var balance = LottoInput.inputBalance();
        final var lotto = new Lotto(balance);

        LottoController lottoController = new LottoController();
        lottoController.buyLotto(lotto);
        lottoController.calculateRank(lotto);
    }
}
