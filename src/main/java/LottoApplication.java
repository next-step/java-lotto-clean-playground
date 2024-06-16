import controller.LottoController;
import domain.NumberGenerator;
import domain.RandomLottoNumberGenerator;

public class LottoApplication {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        NumberGenerator numberGenerator = new RandomLottoNumberGenerator();

        lottoController.execute(numberGenerator);
    }

}
