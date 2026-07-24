import domain.*;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final LottoStore lottoStore = new LottoStore();
    private final LotteryStatistics lotteryStatistics = new LotteryStatistics();

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        Lottos lottos;
        Lotto winningLotto;
        int purchasePrice;

        while(true) {
            try{
                purchasePrice = inputView.inputPrice();

                lottos = lottoStore.buy(new Money(purchasePrice));

                resultView.printLottos(lottos);

                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
           try {
               String winningNumbers = inputView.inputWinningLotto();

               String[] numbers = winningNumbers.split(",");

               List<Integer> lottoNumbers = new ArrayList<>();

               try {
                   for (String number : numbers) {
                       lottoNumbers.add(Integer.parseInt(number.trim()));
                   }
               } catch (NumberFormatException e) {
                   throw new IllegalArgumentException("당첨 번호는 숫자만 입력해주세요.");
               }

               winningLotto = new Lotto(lottoNumbers);

               break;
           } catch (IllegalArgumentException e) {
               System.out.println(e.getMessage());
           }
        }

        lotteryStatistics.calculateStatistics(lottos, winningLotto);

        int totalPrize = lotteryStatistics.calculatePrize();

        resultView.printStatistics(
                lotteryStatistics,
                totalPrize,
                purchasePrice
        );
    }
}
