package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import model.Lotto;
import model.LottoNumber;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public int readPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return readNextInt();
    }

    public int readBonusNumber(Lotto lotto) {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = readNextInt();
        if (lotto.numbers().contains(bonusNumber)) {
            throw new RuntimeException("이미 뽑힌 볼입니다.");
        }
        return bonusNumber;
    }

    public List<Lotto> readManualLottos(Integer count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, count)
            .mapToObj(i -> readLotto())
            .toList();
    }

    public int readManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return readNextInt();
    }

    public Lotto readWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return readLotto();
    }

    private Lotto readLotto() {
        String input = SCANNER.nextLine();
        return new Lotto(
            Stream.of(input.replace(" ","").split(","))
            .map(it -> new LottoNumber(Integer.parseInt(it)))
            .toList()
        );
    }

    private int readNextInt() {
        Integer input = SCANNER.nextInt();
        SCANNER.nextLine();
        return input;
    }
}
