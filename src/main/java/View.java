import java.util.Scanner;

public class View {
    private static final Scanner in = new Scanner(System.in);

    public void inputLottoAmount(){
        int amount;
        try{
            amount = in.nextInt();
            if(amount <= 0) throw new RuntimeException("");
        }
        catch (NumberFormatException e) throw new RuntimeException("");
    }


}
