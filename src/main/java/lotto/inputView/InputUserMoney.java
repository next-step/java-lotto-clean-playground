package lotto.inputView;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUserMoney {
    private static final Pattern PATTERN = Pattern.compile("\\d+");

    public Integer getInput(){
        System.out.println("구매금액을 입력해 주세요.");
        Scanner scan = new Scanner(System.in);
        String userMoney = scan.nextLine();
        checkValidate(userMoney);
        return Integer.parseInt(userMoney);
    }

    public void checkValidate(String userMoney) {
        if (!PATTERN.matcher(userMoney).matches()) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력값입니다. ");
        }
    }
}
