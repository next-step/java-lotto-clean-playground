package lotto.inputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputNumber {

    private static final Pattern PATTERN = Pattern.compile("(\\d{1,2},){5}\\d{1,2}");

    public List<Integer> getInput(){

        System.out.println("\n당첨 번호를 입력해 주세요.");
        Scanner scan = new Scanner(System.in);
        String userNumList = scan.next();
        checkValidate(userNumList);

        List<Integer> userNumbers = new ArrayList<>();
        for ( String num : userNumList.split(",") ) {
            userNumbers.add(Integer.parseInt(num));
        }

        return userNumbers;
    }

    public void checkValidate(String userNumList) {
        if (!PATTERN.matcher(userNumList).matches()) {
            throw new IllegalArgumentException("[ERROR] 올바른 구분자로 입력되어있지 않는 입력값입니다.");
        }
    }
}
