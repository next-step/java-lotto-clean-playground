package support;

import domain.Lotto;
import domain.LottoNumber;
import java.util.Arrays;
import java.util.List;

public class LottoTestHelper {

    public static Lotto lotto(int... nums) {
        return new Lotto(numbers(nums));
    }

    public static List<LottoNumber> numbers(int... nums) {
        return Arrays.stream(nums)
                .mapToObj(LottoNumber::of)
                .toList();
    }
}
