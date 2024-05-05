package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoInput {
    public static int inputBalance() {
        System.out.println("구입금액을 입력해 주세요");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static List<Integer> inputLottoAnswer(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        String[] lottoAnswerStr = str.split(", ");
        List<Integer> lottoAnswer = new ArrayList<>();
        for (String s : lottoAnswerStr) {
            lottoAnswer.add(Integer.parseInt(s));
        }
        return lottoAnswer;
    }

    public static int inputBonusBall(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();

    }

}

