import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class View {
    private static final Scanner in = new Scanner(System.in);

    public void inputLottoAmount(){
        int amount;
        try{
            amount = in.nextInt();
            if(amount < 1000) throw new RuntimeException("");
            if(amount % 1000 != 0) throw new RuntimeException("");
        }
        catch (NumberFormatException e){
            throw new RuntimeException("");
        }
    }

    public void intputWinningNums(){
        in.nextLine();
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