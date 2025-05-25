package lotto;

import java.util.ArrayList;
import java.util.List;

public class WinningNumberParser {
    public static LottoNumber parseBonus(String bonusLine, List<LottoNumber> winningNumbers) {
        try {
            int bonusValue = Integer.parseInt(bonusLine.trim());
            LottoNumber bonus = new LottoNumber(bonusValue);
            if (winningNumbers.contains(bonus)) {
                throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            }
            return bonus;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
        }
    }

    public static List<LottoNumber> parseNumbers(String line) {
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
}
