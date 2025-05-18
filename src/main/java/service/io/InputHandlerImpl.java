package service.io;

import domain.Lotto;
import domain.Lottos;
import domain.LottoNumber;
import domain.WinningLotto;
import service.InputHandler;
import service.assembler.ManualLottoAssembler;
import utils.parser.LottoPurchaseAmountParser;
import utils.parser.ManualLottoCountParser;
import utils.parser.WinningLottoParser;
import utils.parser.BonusNumberParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class InputHandlerImpl implements InputHandler {

    private final InputView inputView;
    private final OutputView outputView;

    public InputHandlerImpl(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public int readPurchaseAmount() {
        outputView.printLottoPurchasePrompt();
        return LottoPurchaseAmountParser.parse(inputView.readLottoPurchaseAmount());
    }

    @Override
    public int readManualLottoCount(int purchaseAmount) {
        outputView.printManualLottoCountPrompt();
        return ManualLottoCountParser.parse(inputView.readManualLottoCount(), purchaseAmount);
    }

    @Override
    public Lottos readManualLottos(int count) {
        List<Lotto> manualLottos = ManualLottoAssembler.assemble(count, inputView, outputView);
        return new Lottos(manualLottos);
    }

    @Override
    public WinningLotto readWinningNumbers() {
        outputView.printLastWeekWinningNumbersPrompt();
        return WinningLottoParser.parse(inputView.readLastWeekWinningNumbers());
    }

    @Override
    public LottoNumber readBonusNumber(WinningLotto winningLotto) {
        outputView.printBonusNumberPrompt();
        return BonusNumberParser.parse(inputView.readBonusNumber(), winningLotto);
    }
}
