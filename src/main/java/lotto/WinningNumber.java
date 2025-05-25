package lotto;


import java.util.List;

public class WinningNumber {
    private final List<LottoNumber> winning;
    private final LottoNumber bonus;

    private WinningNumber(List<LottoNumber> winning, LottoNumber bonus) {
        this.bonus = bonus;
        this.winning = winning;
    }

    public static WinningNumber of(List<LottoNumber> winning, LottoNumber bonus) {
        return new WinningNumber(winning, bonus);
    }

    public List<LottoNumber> getWinning() {
        return winning;
    }

    public LottoNumber getBonus() {
        return bonus;
      
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class WinningNumber {
    private final List<LottoNumber> winning;
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;

    private WinningNumber(List<LottoNumber> winning) {
        this.winning = winning;
    }

    public static WinningNumber of(List<LottoNumber> list) {
        return new WinningNumber(list);
    }

    public static WinningNumber inputWinningNumbers() {
        while (true) {
            String line = readLine();
            try {
                List<LottoNumber> list = parseNumbers(line);
                numberValidate(list);
                duplicateNumber(list);
                return WinningNumber.of(list);
            } catch (IllegalArgumentException e) {
                System.out.println("→ " + e.getMessage() + " 다시 입력해주세요.");
            }
        }
    }
    private static String readLine() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요 (예: 1,2,3,4,5,6): ");
        return new Scanner(System.in).nextLine();
    }

    private static List<LottoNumber> parseNumbers(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("숫자는 반드시 6개여야 합니다.");
        }
        List<LottoNumber> list = new ArrayList<>();
        for (String lottoParts : parts) {
            try {
                list.add(new LottoNumber(Integer.parseInt(lottoParts.trim())));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
            }
        }
        return list;
    }

    private static void numberValidate(List<LottoNumber> list) {
        for (LottoNumber lottoNumber : list) {
            int inputNumber = lottoNumber.getValue();
            if (inputNumber < LOTTO_START_NUMBER || inputNumber > LOTTO_END_NUMBER) {
                throw new IllegalArgumentException("1~45 사이의 숫자만 입력 가능합니다.");
            }
        }
    }

    private static void duplicateNumber(List<LottoNumber> list) {
        if (new HashSet<>(list).size() != list.size()) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }

    public boolean contains(LottoNumber num) {
        return winning.contains(num);
    }

}
