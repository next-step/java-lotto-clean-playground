import controller.LottoController;
import domain.LottoMachine;
import domain.numberGenerator.LottoNumberGenerator;
import domain.numberGenerator.NumberGenerator;
import domain.LottoShop;
import view.LottoView;

public class Main {

    public static void main(String[] args) {

        NumberGenerator numberGenerator = new LottoNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        LottoShop lottoShop = new LottoShop(lottoMachine);

        LottoController lottoController = new LottoController(lottoShop, new LottoView());
        lottoController.run();
    }
}
