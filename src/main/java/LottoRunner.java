import domain.LottoGenerator;
import domain.Lottos;
import dto.LottoNumbers;
import utils.LottoNumbersParser;
import utils.LottoPurchaseAmountParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoRunner {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoRunner(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        outputView.printLottoPurchasePrompt();
        String purchaseAmountInput = inputView.readLottoPurchaseAmount();
        int purchaseAmount = LottoPurchaseAmountParser.parse(purchaseAmountInput);
        System.out.println();

        Lottos lottos = lottoGenerator.generate(purchaseAmount);
        outputView.printLottoPurchaseResultHeader(lottos.count());

        List<LottoNumbers> parsed = LottoNumbersParser.parse(lottos);
        outputView.printLottoNumbers(parsed);
    }
}
