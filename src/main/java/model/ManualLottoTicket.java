package model;

import util.Parser;

import java.util.List;

public class ManualLottoTicket extends LottoTicket {

    public ManualLottoTicket(List<String> manualNumbersStrList) {
        super(generateManualLottos(manualNumbersStrList));
    }

    private static List<Lotto> generateManualLottos(List<String> manualNumbersStrList) {
        return manualNumbersStrList.stream()
            .map(Parser::parseManualLottoNumbers)
            .map(Lotto::new)
            .toList();
    }
}
