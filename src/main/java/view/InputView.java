package view;

import model.LottoPurchaseMoney;

import java.util.Scanner;

public class InputView {

    public static LottoPurchaseMoney inputMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요");
        return new LottoPurchaseMoney(sc.nextInt());
    }
}
