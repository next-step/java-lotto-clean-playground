import controller.LottoController;
import domain.*;

public class LottoApplication {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(
                new LottosCreator(new RandomLottoMakeStrategy()),
                new LottoCountCalculator(),
                new UpdateWinningLottos(new CorrectLottoNumbersCheck()),
                new RateOfReturnCalculator());
        lottoController.startLottoApplication();
    }
}
