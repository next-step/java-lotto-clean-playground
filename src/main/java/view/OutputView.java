package view;

import domain.Lottos;
import domain.Profit;
import domain.Rank;

import java.util.Map;

public interface OutputView {
    void printLottoPurchaseResultHeader(int manualCount, int autoCount);
    void printLottoNumbers(Lottos lottos);
    void printWinningStatistics(Map<Rank, Integer> matchLines);
    void printProfit(String profitLine);
    void printLottoPurchaseAmountPrompt();
    void printManualLottoCountPrompt();
    void printManualPurchaseLottoNumbersPrompt();
    void printLastWeekWinningNumbersPrompt();
    void printBonusNumberPrompt();
    String toProfitMessage(Profit profit);
}
