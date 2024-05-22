package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    public static int inputMoney() { //입력금액
        return Integer.parseInt(userInput());
    }

 //   public static int lottoTotalCount(int inputMoney){
 //        return inputMoney/1000;
 //    }
    public static int inputManualCount() {
        return Integer.parseInt(userInput());
    }

    public static int inputAutoCount(int totalCount, int manualCount){
        return totalCount - manualCount;
    }//

    public static String inputManualNumbers() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }//

    public static String answerNumber() {
        return userInput();
    }

    public static int bonusNumber() {
        return Integer.parseInt(userInput());
    }

    private static String userInput() {//입력도 따로 빼야하나?
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextLine());
    }
}
