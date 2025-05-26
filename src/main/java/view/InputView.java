package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);
    //static 메서드 안에서 sc를 사용하고 싶은데 sc가 static이 아니므로 사용할 수 없음
    //그러므로 이걸 static으로 만듦-> 굳이 인스턴스화 하지 않아도 되는 클래스니까

    public static int getPurchaseAmount() {
        System.out.println("구매 금액을 입력해주세요.");
        try {//sc를 static에 넣을 수 없어서 어쩔 수 없이 변수로 했는데 방법 있는지?
            return sc.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("숫자 외의 값이 입력됨");
            throw e;
        }
    }
}
