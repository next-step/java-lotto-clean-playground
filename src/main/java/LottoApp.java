import java.util.List;

public class LottoApp {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        int purchaseAmount = lottoMachine.getPurchaseAmount();
        int numberOfLottos = lottoMachine.calculateNumberOfLottos(purchaseAmount);
        lottoMachine.printPurchaseInfo(numberOfLottos);
        List<Lotto> lottos = lottoMachine.generateLottos(numberOfLottos);
        lottoMachine.printLottos(lottos);
    }
}
