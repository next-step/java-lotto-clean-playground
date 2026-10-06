import controller.Controller;
import lottoGenerator.RandomLottoGenerator;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new RandomLottoGenerator());
        controller.run();
    }
}
