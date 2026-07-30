import controller.Controller;
import model.Lotto;
import view.View;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        View view=new View();
        int cost=view.inputCost();
        List<Lotto> lottos;

        Controller controller=new Controller(cost);
        lottos=controller.genLotto();
        view.printLottos(lottos);

    }
}
