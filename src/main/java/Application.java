import domain.*;
import view.*;

//MVC : Controller
public class Application {
    public static void main(String[] args) {
        Wallet wallet = new Wallet(new Money(InputView.inputMoneyFromUser()));
        wallet.buyAutomatedLotto();
        ResultView.printResultOfPurchase(wallet);
        WinningNumbers winningNumbers =  InputView.inputWinningNumberFromUser();
        ResultView.printResultOfWinning(wallet, winningNumbers);
    }
}
