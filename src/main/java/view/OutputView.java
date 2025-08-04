package view;

import java.util.List;

public class OutputView {

    private final static String BUY_MESSAGE = "개를 구매했습니다.";

    public void countPrint (int count){
        System.out.println("\n" + count + BUY_MESSAGE);

    }
    public void lottoPrint(List<Integer> numbers) {
        System.out.println(numbers);
    }
}
