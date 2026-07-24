import domain.LottoSystem;
import generator.RandomLottoNumberGenerator;
import view.InputView;
import view.ResultView;

public class MissionMain {
    public static void main(String[] args) {
        final var inputMoney = InputView.inputMoney();
        LottoSystem lottoSystem = new LottoSystem(inputMoney, new RandomLottoNumberGenerator());
        ResultView.printLottoResult(lottoSystem.getPurchasedLottoNumbers());
    }
}
