package service.io;

import domain.BonusNumber;
import domain.Lotto;
import domain.Lottos;
import domain.WinningNumbers;
import service.InputHandler;
import service.assembler.ManualLottoAssembler;
import utils.parser.BonusNumberParser;
import utils.parser.LottoPurchaseAmountParser;
import utils.parser.ManualLottoCountParser;
import utils.parser.WinningNumbersParser;
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
    public WinningNumbers readWinningNumbers() {
        outputView.printLastWeekWinningNumbersPrompt();
        return WinningNumbersParser.parse(inputView.readLastWeekWinningNumbers());
    }

    @Override
    public BonusNumber readBonusNumber(WinningNumbers winningNumbers) {
        outputView.printBonusNumberPrompt();
        return BonusNumberParser.parse(inputView.readBonusNumber(), winningNumbers);
    }
}
