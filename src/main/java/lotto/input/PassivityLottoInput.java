package lotto.input;

import lotto.domain.InputLottoNumber;
import lotto.domain.LottoNumber;
import lotto.parser.WinningNumberParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassivityLottoInput {
    private final WinningNumberParser winningNumberParser;
    private final Scanner scanner = new Scanner(System.in);

    public PassivityLottoInput(WinningNumberParser winningNumberParser) {
        this.winningNumberParser = winningNumberParser;
    }

    public List<InputLottoNumber> inputPassivityTicket(int count) {
        List<InputLottoNumber> passivityTicket = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            System.out.print(i + "번째 수동 로또 번호를 입력해주세요: ");
            String line = scanner.nextLine();
            List<LottoNumber> number = winningNumberParser.parseNumbers(line);
            passivityTicket.add(InputLottoNumber.of(number));
        }
        return passivityTicket;
    }
}
