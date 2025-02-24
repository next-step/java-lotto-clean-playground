import domain.*;
import view.*;

//MVC : Controller
public class Application {
    public static void main(String[] args) {
        Wallet wallet = new Wallet(new Money(InputView.inputMoneyFromUser()));
        long numberOfManualLottos = InputView.inputAmountOfManualLottosFromUser();

        for(long i = 0; i < numberOfManualLottos; i++){
            wallet.buyManualLotto(InputView.inputLottoNumberFromUser());
        }
        long numberOfAutomatedLottos = wallet.buyAutomatedLotto();

        ResultView.printResultOfPurchase(wallet, numberOfManualLottos, numberOfAutomatedLottos);
        WinningNumbers winningNumbers =  InputView.inputWinningNumberFromUser();
        wallet.runRankChecks(winningNumbers);
        ResultView.printResultOfWinning(wallet);
    }
}
