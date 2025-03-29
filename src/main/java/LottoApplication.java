import controller.LottoController;
import domain.LottoNumberGenerator;
import domain.RandomLottoNumberGenerator;

public class LottoApplication {

    private static final LottoNumberGenerator generator = new RandomLottoNumberGenerator();

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(generator);
        lottoController.run();
    }
}
