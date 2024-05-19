package view;

import java.util.Scanner;

public class InputView {

    Scanner input=new Scanner(System.in);
    public int inputPrice(){
        System.out.println("구입 금액을 입력해주세요.");
        return input.nextInt();
    }
}
