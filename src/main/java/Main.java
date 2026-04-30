import controller.Controller;
import domain.LottoService;
import domain.Statistic;
import domain.RandomLottoGenerator;
import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(
                new InputView(),
                new OutputView(),
                new LottoService(new RandomLottoGenerator()),
                new Statistic()
        );
        controller.run();
    }
}
