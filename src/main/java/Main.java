import controller.AutoLottoController;
import inputView.Price;
import model.LottoNumbers;
import model.LottoNumbersRepository;

public class Main {
    public static void main(String[] args) {
        AutoLottoController controller = new AutoLottoController();

        // 1. 구입 금액 입력
        Price money = controller.showPrice();

        // 2. 로또 번호 생성
        LottoNumbersRepository allLotteries = controller.showLottoNumbers(money.howManyLottos());

        // 3. 당첨 번호 입력
        LottoNumbers lastLotto = controller.readLastLotto();

        // 4. 통계 출력
        controller.showLotteryStatistics(allLotteries.readLottoNumbersRepository()
                , lastLotto.getNumbers()
                , money.getValue());
    }
}
