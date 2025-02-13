package fixture;

import domain.Lotto;

import java.util.List;

public class LottoFixture {

    public static final List<Integer> testNumbersOneToSix = List.of(1, 2, 3, 4, 5, 6);
    public static final List<Integer> testNumbersSevenToTwelve = List.of(7, 8, 9, 10, 11, 12);
    public static final List<Integer> testNumbersFortyToFortyFive = List.of(40, 41, 42, 43, 44, 45);
    public static final Lotto testLottoOneToSix = new Lotto(testNumbersOneToSix);
    public static final Lotto testLottoSevenToTwelve = new Lotto(testNumbersSevenToTwelve);
    public static final Lotto testLottoFortyToFortyFive = new Lotto(testNumbersFortyToFortyFive);
}
