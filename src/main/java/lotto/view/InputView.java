package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    //구입금액 입력 요청
    public int requestPurchasePrice(){
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    //당첨번호 입력 요청
    public String requestSuccessNum(){
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    //보너스볼 입력 요청
    public Integer requestBonusBall(){
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }

    //수동으로 구매할 로또 수 입력 요청
    public Integer requestPassivityPurchase(){
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    //수동으로 구매할 로또 번호 입력
    public List<String> requestPassivityNum(int passivityPurchaseNum){
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> passivityNums = new ArrayList<>();
        scanner.nextLine();
        for(int i=0;i<passivityPurchaseNum;i++){
            passivityNums.add(scanner.nextLine());
        }
        return passivityNums;
    }
}
