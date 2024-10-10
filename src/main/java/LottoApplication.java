import controller.LottoController;
import domain.*;

public class LottoApplication {

    public static void main(String[] args) {
        final LottoController lottoController = new LottoController(
                new UpdateWinningLottos(new CorrectLottoNumbersCheck()),
                new LottoCalculator());
        lottoController.startLottoApplication();
    }
}
