package view;

import java.util.List;

public class OutputView {

    private final static String BUY_MESSAGE = "개를 구매했습니다.";
    private final static String THREE_MATCHES = "3개 일치 (5000원)- ";
    private final static String FOUR_MATCHES = "4개 일치 (50000원)- ";
    private final static String FIVE_MATCHES = "5개 일치 (1500000원)- ";
    private final static String SIX_MATCHES = "6개 일치 (2000000000원)- ";
    private final static String COUNT_SUFFIX = "개";

    public void printTicketCount (int count){
        System.out.println("\n" + count + BUY_MESSAGE);

    }
    public void printLottoNumbers(List<Integer> numbers) {
        System.out.println(numbers);
    }

    public void printResults(int [] arr, double rate){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(THREE_MATCHES + arr[0] + COUNT_SUFFIX);
        System.out.println(FOUR_MATCHES + arr[1] + COUNT_SUFFIX);
        System.out.println(FIVE_MATCHES + arr[2] + COUNT_SUFFIX);
        System.out.println(SIX_MATCHES + arr[3] + COUNT_SUFFIX);

        System.out.println("총 수익률은 " + rate + " 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}
