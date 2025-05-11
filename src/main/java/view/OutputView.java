package view;

import dto.LottoNumbersDto;
import dto.PrintableMatchDto;
import dto.PrintableProfitDto;

import java.util.List;

public class OutputView {

    private static final String LOTTO_PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String LOTTO_PURCHASE_RESULT_HEADER = "%d개를 구매했습니다.";
    private static final String LAST_WEEK_WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요.";

    private static final String WINNING_RESULT_HEADER_TITLE = "당첨 통계";
    private static final String WINNING_RESULT_HEADER_LINE = "---------";

    public void printLottoPurchasePrompt() {
        System.out.println(LOTTO_PURCHASE_AMOUNT_PROMPT);
    }

    public void printLottoPurchaseResultHeader(int ticketCount) {
        System.out.printf(LOTTO_PURCHASE_RESULT_HEADER + "%n", ticketCount);
    }

    public void printLottoNumbers(List<LottoNumbersDto> numbers) {
        for (LottoNumbersDto lottoNumbersDto : numbers) {
            System.out.println(lottoNumbersDto);
        }
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
