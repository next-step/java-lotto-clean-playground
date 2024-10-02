import controller.LottoController;
import domain.*;

public class LottoApplication {

    public static void main(String[] args) {
        final LottoController lottoController = new LottoController(
                new LottoCountCalculator(),
                new UpdateWinningLottos(new CorrectLottoNumbersCheck()),
                new RateOfReturnCalculator());
        lottoController.startLottoApplication();
    }
}
