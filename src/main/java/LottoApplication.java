import controller.LottoController;
import view.LottoView;

public class LottoApplication {

    public static void main(String[] args) {
        LottoView view = new LottoView();
        new LottoController(view).run();
    }
}
