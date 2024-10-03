package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {
    private final List<Integer> capableNumber = new ArrayList<>(Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
            30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
            40, 41, 42, 43, 44, 45
    ));

    private static final int RANDOM_INDEX = 0;
    public LottoNumbers generateLottoNumbers() {
        // 이걸로 배열 등록하면 Collections shuffle 에서 터짐 왜?
//        List<Integer> capableNumber = List.of(
//                1, 2, 3, 4, 5, 6, 7, 8, 9,
//                11, 12, 13, 14, 15, 16, 17, 18, 19,
//                20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
//                30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
//                40, 41, 42, 43, 44, 45
//        );

        Collections.shuffle(capableNumber);
        return new LottoNumbers(
                capableNumber.subList(0, LottoNumbers.SIZE)
                        .stream()
                        .sorted()
                        .map(LottoNumber::new)
                        .toList()
        );
    }
}
