package utils;

import domain.Lotto;
import domain.LottoNumbers;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoAssembler {

    public static List<Lotto> assemble(int manualCount, InputView inputView, OutputView outputView) {
        outputView.printManualPurchaseLottoNumbersPrompt();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < manualCount; i++) {
            String input = inputView.readManualLottoNumbersLine();
            LottoNumbers numbers = LottoNumbersInputParser.parse(input);
            lottos.add(new Lotto(numbers));
        }

        return lottos;
    }
}
