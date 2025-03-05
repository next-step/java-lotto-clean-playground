package fixture;

import model.LottoNumber;
import model.LottoNumbers;
import model.LottoRank;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum LottoNumbersFixture {

    WINNING_LOTTO(getLottoNumbersFromIntegers(1, 2, 3, 4, 5, 6), LottoRank.SIX_EQUALS),
    THREE_EQUALS_LOTTO(getLottoNumbersFromIntegers(1, 2, 3, 43, 44, 45), LottoRank.THREE_EQUALS),
    FOUR_EQUALS_LOTTO(getLottoNumbersFromIntegers(1, 2, 3, 4, 44, 45), LottoRank.FOUR_EQUALS),
    FIVE_EQUALS_LOTTO(getLottoNumbersFromIntegers(1, 2, 3, 4, 5, 45), LottoRank.FIVE_EQUALS),
    SIX_EQUALS_LOTTO(getLottoNumbersFromIntegers(1, 2, 3, 4, 5, 6), LottoRank.SIX_EQUALS);

    public static final LottoNumber NOT_EQUALS_BONUS_BALL = new LottoNumber(33);

    private final LottoNumbers lottoNumbers;
    private final LottoRank expectedRank;

    LottoNumbersFixture(LottoNumbers lottoNumbers, LottoRank expectedRank) {
        this.lottoNumbers = lottoNumbers;
        this.expectedRank = expectedRank;
    }

    public static List<LottoNumbersFixture> getFixturesExcludeWinningLotto() {
        return Arrays.stream(values())
                .filter(lottoNumbersFixture -> lottoNumbersFixture != WINNING_LOTTO)
                .toList();
    }

    public LottoNumbers getValue() {
        return lottoNumbers;
    }

    public LottoRank getExpectedRank() {
        return expectedRank;
    }

    private static LottoNumbers getLottoNumbersFromIntegers(int... numbers) {
        Set<LottoNumber> lottoNumbers = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toUnmodifiableSet());

        return new LottoNumbers(lottoNumbers);
    }

}
