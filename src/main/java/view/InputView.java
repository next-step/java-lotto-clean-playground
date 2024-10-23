package view;

import model.LottoNumber;
import model.LottoTicket;
import model.LottoTickets;

import java.util.Scanner;

public class InputView {

    public int inputPurchaseAmount(){
        System.out.println("구매금액을 입력해 주세요.");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public int inputManualLottoTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public LottoTickets inputManualTicketNumbers(int manualLottoTicketCount){
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        Scanner scanner = new Scanner(System.in);

        LottoTickets manualLottoTickets = new LottoTickets();

        for (int i = 0; i < manualLottoTicketCount; i++) {

            String manualTicketNumbers = scanner.nextLine();
            manualLottoTickets.addTicket(LottoTicket.transformStringToLottoTicket(manualTicketNumbers));

        }

        return manualLottoTickets;
    }

    public LottoTicket inputLastWeekWinningNumbers(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        Scanner scanner = new Scanner(System.in);
        String lastWeekWinningNumber = scanner.nextLine();

        return LottoTicket.transformStringToLottoTicket(lastWeekWinningNumber);
    }

    public LottoNumber inputBonusBallNumber(){
        System.out.println("보너스 볼을 입력해 주세요.");

        Scanner scanner = new Scanner(System.in);
        return new LottoNumber(scanner.nextInt());
    }
}
