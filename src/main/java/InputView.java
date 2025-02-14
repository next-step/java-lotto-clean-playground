import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner in = new Scanner(System.in);

    public int inputLottoAmount(){
        System.out.println("구입금액을 입력해 주세요.");
        int amount;
        try{
            amount = Integer.parseInt(in.nextLine());
            if(amount < 1000) throw new RuntimeException("");
            if(amount % 1000 != 0) throw new RuntimeException("");
        }
        catch (NumberFormatException e){
            throw new RuntimeException("");
        }
        return amount;
    }

    public List<Integer> intputWinningNums(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return validateWiningNumber(in.nextLine());
    }

    private List<Integer> validateWiningNumber(String winningNums){
        List<Integer> winingNumbers;
        try{
            winingNumbers = Arrays.stream(winningNums.split(","))
                    .map(Integer::parseInt)
                    .toList();
        }
        catch (NumberFormatException e){
            throw new RuntimeException("");
        }
        return winingNumbers;
    }
}