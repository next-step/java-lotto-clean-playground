import controller.LottoController;
import inputView.PurchaseAmount;
import model.LottoNumber;
import model.LottoNumbers;
import model.LottoTicketBundle;

public class Main {
    public static void main(String[] args) {
        LottoController controller = new LottoController();

        // 1. 구입 금액 입력
        PurchaseAmount purchaseAmount = controller.askPurchaseAmount();

        // 2. 수동 및 자동 로또 번호 생성
        LottoTicketBundle allTickets = controller.buyTickets(purchaseAmount.howManyLottos());

        // 3. 당첨 번호 입력
        LottoNumbers winningNumbers = controller.askWinningNumbers();

        // 4. 보너스 볼 입력
        LottoNumber bonusBall = controller.askBonusBall();

        // 5. 통계 출력
        controller.showStatistics(allTickets.readLottoNumbersRepository(),
                winningNumbers.getNumbers(),
                purchaseAmount.getValue(),
                bonusBall
        );
    }
}
