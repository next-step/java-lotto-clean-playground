package view;

import dto.LottoNumbersDto;
import dto.PrintableMatchDto;
import dto.PrintableProfitDto;

import java.util.List;

public class OutputView {
    private static final String LOTTO_PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT_PROMPT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBERS_PROMPT = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String LOTTO_PURCHASE_RESULT_HEADER_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String LAST_WEEK_WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_RESULT_HEADER_TITLE = "당첨 통계";
    private static final String WINNING_RESULT_HEADER_LINE = "---------";

    public void printLottoPurchasePrompt() {
        System.out.println(LOTTO_PURCHASE_AMOUNT_PROMPT);
    }

    public void printManualLottoCountPrompt() {
        System.out.println(MANUAL_LOTTO_COUNT_PROMPT);
    }

    public void printManualPurchaseLottoNumbersPrompt() {
        System.out.println(MANUAL_LOTTO_NUMBERS_PROMPT);
    }

    public void printLottoPurchaseResultHeader(int manualCount, int autoCount) {
        System.out.printf(LOTTO_PURCHASE_RESULT_HEADER_FORMAT, manualCount, autoCount);
    }

    public void printLottoNumbers(List<LottoNumbersDto> numbers) {
        for (LottoNumbersDto lottoNumbersDto : numbers) {
            System.out.println(lottoNumbersDto);
        }
    }

    public void printBonusNumberPrompt() {
        System.out.println(BONUS_NUMBER_PROMPT);
    }

    public void printLastWeekWinningNumbersPrompt() {
        System.out.println(LAST_WEEK_WINNING_NUMBERS_PROMPT);
    }

    public void printWinningStatistics(List<PrintableMatchDto> matchLines) {
        System.out.println(WINNING_RESULT_HEADER_TITLE);
        System.out.println(WINNING_RESULT_HEADER_LINE);
        for (PrintableMatchDto matchLine : matchLines) {
            System.out.println(matchLine.messageLine());
        }
    }

    public void printProfit(PrintableProfitDto profitLine) {
        System.out.println(profitLine.messageLine());
    }
}
