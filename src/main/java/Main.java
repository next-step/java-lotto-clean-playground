import controller.LottoController;
import domain.Lotto;
import domain.LottoGame;
import view.InputView;

public class Main {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.startGame();
    }

}
