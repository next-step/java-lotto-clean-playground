package view;

import util.InputValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner in = new Scanner(System.in);

    public int inputLottoAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return InputValidator.validateLottoAmount(in.nextLine());
    }

    public int manualLottoAmount(int lottoAmount) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return InputValidator.validateManualLottoAmount(lottoAmount, in.nextLine());
    }

    public List<List<Integer>> inputManualNumbers(int count) {
        List<List<Integer>> list = new ArrayList<>();

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        for (int i = 0; i < count; ++i) {
            String line = in.nextLine();
            List<Integer> numbers = Arrays.stream(line.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            list.add(numbers);
        }
        return list;
    }

    public List<Integer> inputWinningNums() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return validateLottoNumber(in.nextLine());
    }

    public int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return InputValidator.validateBonusBallNumber(in.nextLine());
    }

    private List<Integer> validateLottoNumber(String winningNums) {
        List<Integer> winningNumbers;
        try{
            winningNumbers = Arrays.stream(winningNums.split(","))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .toList();
        }
        catch (NumberFormatException e){
            throw new RuntimeException("잘못된 로또 번호 입니다.");
        }
        return winningNumbers;
    }
}
