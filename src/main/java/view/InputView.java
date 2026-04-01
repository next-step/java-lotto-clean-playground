package view;

import domain.Lotto;
import domain.LottoNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int getMoney(){
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public Lotto getWinnerNumbers(){
        System.out.println("지난 주 당첨번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseToLotto(input);
    }

    public Lotto parseToLotto(String input){
       String[] tokens = input.split(", ");
        List<LottoNumber> winningNumbers = new ArrayList<>();
        for (String token : tokens){
            winningNumbers.add(new LottoNumber(Integer.parseInt(token)));
        }

        return new Lotto(winningNumbers);
    }


}
