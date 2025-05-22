package view;

import domain.Lotto;
import domain.Profit;
import domain.Rank;

import java.util.List;
import java.util.Map;

public interface OutputView {
    void printLottoPurchaseResultHeader(int manualCount, int autoCount);
    void printLottoNumbers(List<Lotto> lottos);
    void printWinningStatistics(Map<Rank, Integer> matchLines);
    void printProfit(String profitLine);
    void printLottoPurchaseAmountPrompt();
    void printManualLottoCountPrompt();
    void printManualPurchaseLottoNumbersPrompt();
    void printLastWeekWinningNumbersPrompt();
    void printBonusNumberPrompt();
    String toProfitMessage(Profit profit);
}
