package domain;

import java.util.Arrays;
import java.util.List;

public class WinLotto {

    private final Lotto lotto;

    private WinLotto(Lotto lotto){
        this.lotto = lotto;
    }

    public static WinLotto createWinLotto(String numberString){
        List<Integer> LottoNumbers = parseIntegerList(numberString);

        return new WinLotto(new Lotto(LottoNumbers));
    }

    private static List<Integer> parseIntegerList(String numberString) {
        return Arrays.stream(numberString.split("\\s*,\\s*"))
                .map(Integer::parseInt)
                .toList();
    }

    public LottoRank calculateRank(Lotto comparedLotto){
        return LottoRank.of(getMatchedCount(comparedLotto));
    }

    private int getMatchedCount(Lotto comparedLotto) {
        List<Integer> comparedLottoNumbers = comparedLotto.getNumbers();

        return lotto.getNumbers()
                .stream()
                .filter(comparedLottoNumbers::contains)
                .toList()
                .size();
    }
}
