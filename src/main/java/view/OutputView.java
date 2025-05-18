package view;

import domain.Lotto;
import domain.Lottos;
import domain.Profit;
import domain.Rank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LOTTO_PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT_PROMPT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBERS_PROMPT = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String LAST_WEEK_WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요.";
    private static final String PURCHASE_RESULT_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String MATCH_RESULT_FORMAT = "%d개 일치 (%d원)- %d개%n";
    private static final String WINNING_RESULT_HEADER_TITLE = "당첨 통계";
    private static final String WINNING_RESULT_HEADER_LINE = "---------";
    private static final String BONUS_MATCH_RESULT_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원)- %d개%n";
    private static final String PROFIT_MESSAGE_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)";
    private static final int MIN_MATCH_COUNT = 3;
    private static final String PROFIT_RESULT_GAIN = "이득";
    private static final String PROFIT_RESULT_LOSS = "손해";
    private static final String LOTTO_NUMBER_OPENING = "[";
    private static final String LOTTO_NUMBER_CLOSING = "]";
    private static final String LOTTO_NUMBER_SEPARATOR = ", ";

    public void printLottoPurchaseResultHeader(int manualCount, int autoCount) {
        System.out.printf(PURCHASE_RESULT_FORMAT, manualCount, autoCount);
    }

    public void printLottoNumbers(Lottos lottos) {
        List<String> numbers = convertLottoNumbersToStringList(lottos);
        numbers.forEach(System.out::println);
    }

    public void printWinningStatistics(Map<Rank, Integer> matchLines) {
        System.out.println(WINNING_RESULT_HEADER_TITLE);
        System.out.println(WINNING_RESULT_HEADER_LINE);
        matchLines.forEach(this::printMatchStatistics);
    }

    public void printProfit(String profitLine) {
        System.out.println(profitLine);
    }

    public void printLottoPurchaseAmountPrompt() {
        System.out.println(LOTTO_PURCHASE_AMOUNT_PROMPT);
    }

    public void printManualLottoCountPrompt() {
        System.out.println(MANUAL_LOTTO_COUNT_PROMPT);
    }

    public void printManualPurchaseLottoNumbersPrompt() {
        System.out.println(MANUAL_LOTTO_NUMBERS_PROMPT);
    }

    public void printLastWeekWinningNumbersPrompt() {
        System.out.println(LAST_WEEK_WINNING_NUMBERS_PROMPT);
    }

    public void printBonusNumberPrompt() {
        System.out.println(BONUS_NUMBER_PROMPT);
    }

    public String toProfitMessage(Profit profit) {
        double profitRate = profit.rate();
        String result = getProfitResult(profit);
        return String.format(PROFIT_MESSAGE_FORMAT, profitRate, result);
    }

    private List<String> convertLottoNumbersToStringList(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(this::convertLottoToString)
                .collect(Collectors.toList());
    }

    private String convertLottoToString(Lotto lotto) {
        if (lotto.getNumbers().isEmpty()) {
            return LOTTO_NUMBER_OPENING + LOTTO_NUMBER_CLOSING;
        }
        return LOTTO_NUMBER_OPENING + lotto.getNumbers().stream()
                .map(number -> String.valueOf(number.value()))
                .collect(Collectors.joining(LOTTO_NUMBER_SEPARATOR)) + LOTTO_NUMBER_CLOSING;
    }

    private void printMatchStatistics(Rank rank, int count) {
        if (isMatchCountAboveThreshold(rank)) {
            printFormattedMatchResult(rank, count);
        }
    }

    private boolean isMatchCountAboveThreshold(Rank rank) {
        return rank.getMatchCount() >= MIN_MATCH_COUNT;
    }

    private void printFormattedMatchResult(Rank rank, int count) {
        if (isSecondRank(rank)) {
            printBonusMatchResult(rank, count);
        }

        if (!isSecondRank(rank)) {
            printRegularMatchResult(rank, count);
        }
    }

    private void printBonusMatchResult(Rank rank, int count) {
        System.out.printf(BONUS_MATCH_RESULT_FORMAT, rank.getMatchCount(), rank.getPrize(), count);
    }

    private void printRegularMatchResult(Rank rank, int count) {
        System.out.printf(MATCH_RESULT_FORMAT, rank.getMatchCount(), rank.getPrize(), count);
    }

    private boolean isSecondRank(Rank rank) {
        return rank == Rank.SECOND;
    }

    private String getProfitResult(Profit profit) {
        if (profit.isLoss()) {
            return PROFIT_RESULT_LOSS;
        }
        return PROFIT_RESULT_GAIN;
    }
}
