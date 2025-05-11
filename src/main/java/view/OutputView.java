package view;

import dto.LottoNumbers;

import java.util.List;

public class OutputView {
    private final static String LOTTO_PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private final static String LOTTO_PURCHASE_RESULT_HEADER = "%d개를 구매했습니다.";

    public void printLottoPurchasePrompt() {
        System.out.println(LOTTO_PURCHASE_AMOUNT_PROMPT);
    }

    public void printLottoPurchaseResultHeader(int ticketCount) {
        System.out.printf((LOTTO_PURCHASE_RESULT_HEADER) + "%n", ticketCount);
    }

    public void printLottoNumbers(List<LottoNumbers> numbers) {
        for (LottoNumbers lottoNumbers : numbers) {
            System.out.println(lottoNumbers);
        }
    }
}
