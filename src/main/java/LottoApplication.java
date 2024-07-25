import domain.common.Money;
import domain.lotto.IssuanceType;
import domain.lotto.LottoNumber;
import domain.lotto.LottoResult;
import domain.lotto.LottoStore;
import domain.lotto.LottoTicket;
import domain.lotto.ManualCount;
import domain.lotto.dto.StatistsDto;
import domain.lotto.generator.AutoLottoGenerator;
import domain.lotto.generator.LottoGenerator;
import domain.lotto.generator.ManualLottoGenerator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public void start() {
        final Money money = InputView.inputMoney();

        final ManualCount manualCount = InputView.inputManualCount();

        LottoStore lottoStore = new LottoStore(initGenerators());

        List<LottoTicket> lottoTickets = lottoStore.sellLottos(money, manualCount);

        OutputView.printResult(lottoTickets);

        final LottoTicket winningTicket = InputView.inputWinningNumbers();
        final LottoNumber bonusNumber = InputView.inputBonusNumber();

        final LottoResult lottoResult = new LottoResult(winningTicket, bonusNumber);

        StatistsDto statistsDto = lottoResult.makeStatistics(money, lottoTickets);

        OutputView.printStatistics(statistsDto);
    }

    private static Map<IssuanceType, LottoGenerator> initGenerators() {
        Map<IssuanceType, LottoGenerator> lottoGenerators = new HashMap<>();
        lottoGenerators.put(IssuanceType.MANUAL, new ManualLottoGenerator());
        lottoGenerators.put(IssuanceType.AUTO, new AutoLottoGenerator());
        return lottoGenerators;
    }

    public static void main(String[] args) {
        LottoApplication lottoApplication = new LottoApplication();
        lottoApplication.start();
    }
}
