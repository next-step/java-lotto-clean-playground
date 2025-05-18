package view;

import model.Lotto;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import model.LottoNumber;
import java.util.stream.Collectors;
import model.LottoStatistics;
import model.Rank;

public class LottoView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final int LOTTO_NUMBER_COUNT = 6;

    public int readPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요");
        String input = scanner.nextLine().trim();
        int amount;

        try {
            amount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("0이상의 값을 입력하세요.");
        }
        return amount;
    }

    public void printLottoCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLotto(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public List<LottoNumber> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요");
        String input = scanner.nextLine().trim();
        try {
            List<LottoNumber> winningNumbers = Arrays.stream(input.split(DELIMITER)).map(String::trim)
                    .map(Integer::parseInt)
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());

            if (winningNumbers.size() != LOTTO_NUMBER_COUNT) {
                throw new IllegalArgumentException("로또 번호는 6개를 입력해야 합니다.");
            }

            return winningNumbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력 형식 오류: 숫자만 콤마로 구분하여 입력해주세요.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("입력 값 오류: " + e.getMessage());
        }
    }

    public void printStatistics(LottoStatistics statistics) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (5000원) - %d개%n", statistics.getCount(Rank.FIFTH));
        System.out.printf("4개 일치 (50000원) - %d개%n", statistics.getCount(Rank.FOURTH));
        System.out.printf("5개 일치 (1500000원) - %d개%n", statistics.getCount(Rank.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치 (30000000원) - %d개%n", statistics.getCount(Rank.SECOND));
        System.out.printf("6개 일치 (2000000000원) - %d개%n", statistics.getCount(Rank.FIRST));
        System.out.printf("총 수익률은 %.2f입니다.%n", statistics.getProfitRate());
    }

    public LottoNumber readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine().trim());
        return new LottoNumber(number);
    }
}
