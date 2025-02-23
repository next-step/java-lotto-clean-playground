import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner in = new Scanner(System.in);

    public int inputLottoAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount;
        try{
            amount = Integer.parseInt(in.nextLine());
            if(amount < 1000) throw new RuntimeException("1000원 이상 구매하여야 합니다.");
            if(amount % 1000 != 0) throw new RuntimeException("1000원 단위로 구매하여야 합니다.");
        }
        catch (NumberFormatException e) {
            throw new RuntimeException("잘못된 값을 입력하였습니다.");
        }
        return amount;
    }

    public int manualLottoAmount(int lottoAmount) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int amount;
        try{
            amount = Integer.parseInt(in.nextLine());
            if(amount > lottoAmount) throw new RuntimeException("총 구매할 로또 수 이하로 구매하여야 합니다.");
            if(amount <= 0) throw new RuntimeException("1개 이상 구매하여야 합니다.");
        }
        catch (NumberFormatException e){
            throw new RuntimeException("잘못된 값을 입력하였습니다.");
        }
        return amount;
    }

    public List<Integer> inputManualLottoNums() {
        return validateLottoNumber(in.nextLine());
    }

    public List<Integer> intputWinningNums() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return validateLottoNumber(in.nextLine());
    }

    public int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBall;
        try{
            bonusBall = Integer.parseInt(in.nextLine());
            if(bonusBall < 0 || bonusBall > 45) throw new RuntimeException("로또 번호는 1이상 45이하여야 합니다.");
        }
        catch (NumberFormatException e){
            throw new RuntimeException("잘못된 값을 입력하였습니다.");
        }
        return bonusBall;
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