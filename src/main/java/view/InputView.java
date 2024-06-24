package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int getPrice(){
        try{
            System.out.println("구입금액을 입력해 주세요.");
            int price = scanner.nextInt();
            minCheck(price);
            return price;
        } catch (Exception e){
            throw new IllegalArgumentException("금액은 정수로 입력해주세요.");
        }
    }

    public void minCheck(int price){
        if(price < 1000){
            throw new IllegalArgumentException("최소 금액은 1000원 입니다.");
        }
    }
}
