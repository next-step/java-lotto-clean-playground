package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinningNumbers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.LottoParser;

public class InputView {

  private static final Scanner scanner = new Scanner(System.in);

  public long getPurchaseMoney() {
    System.out.println("구입금액을 입력해 주세요.");
    return scanner.nextLong();
  }

  public int getManualTicketCount() {
    System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
    return scanner.nextInt();
  }

  public List<Lotto> getManualLottoTickets(int manualTicketCount) {
    if (manualTicketCount > 0) {
      System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }
    scanner.nextLine();
    List<Lotto> manualLottos = new ArrayList<>();
    for (int i = 0; i < manualTicketCount; i++) {
      String input = scanner.nextLine();
      List<LottoNumber> numbers = LottoParser.parseLottoNumbers(input);
      manualLottos.add(Lotto.createManual(numbers));
    }
    return manualLottos;
  }

  public WinningNumbers getWinningNumbers() {
    System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    String input = scanner.nextLine();
    List<LottoNumber> numbers = LottoParser.parseLottoNumbers(input);
    return new WinningNumbers(numbers);
  }

  public LottoNumber getBonusNumber() {
    System.out.println("\n보너스 볼을 입력해 주세요.");
    int number = Integer.parseInt(scanner.nextLine());
    return new LottoNumber(number);
  }
}
