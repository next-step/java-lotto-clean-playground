import controller.LottoController;
import domain.LottoCountCalculator;
import domain.LottosCreator;
import domain.RandomLottoMakeStrategy;

public class LottoApplication {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(
                new LottosCreator(new RandomLottoMakeStrategy()),
                new LottoCountCalculator());
        lottoController.startLottoApplication();
    }
}
