import model.LottoGame;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGame lottoGame = new LottoGame(inputView, outputView);
        lottoGame.run();
    }
}
