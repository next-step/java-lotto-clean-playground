package lotto;

import java.util.List;

public record LottoReceipt(List<Lotto> lottoRows) {
    public void printToConsole() {
        for (Lotto lottoRow : lottoRows) {
            lottoRow.printToConsole();
        }
    }
}
