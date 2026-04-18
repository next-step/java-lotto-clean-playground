package lotto;

import java.util.Scanner;

public class LottoPlay {
    private static final LottoParser LOTTO_PARSER = new LottoParser();
    private static final Scanner SCANNER = new Scanner(System.in);

    public LottoDraw runDraw(LottoReceipt receipt) {
        System.out.println("\n지난주 당첨 번호를 입력해 주세요.");
        Lotto winningLotto = LOTTO_PARSER.parse(SCANNER.nextLine());
        LottoNumber bonus = getValidBonus(winningLotto);
        return new LottoDraw(winningLotto, bonus, receipt);
    }

    private static LottoNumber bonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int bonusValue = Integer.parseInt(SCANNER.nextLine());
            return new LottoNumber(bonusValue);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요.");
            return bonusBall();
        }
    }

    private static LottoNumber getValidBonus(Lotto winningLotto) {
        while (true) {
            LottoNumber bonus = bonusBall();
            if (!winningLotto.numbers().contains(bonus)) {
                return bonus;
            }
            System.out.println("보너스 볼은 당첨 번호와 같을 수 없습니다.");
        }
    }
}
