package domain.util;

import domain.model.LottoNumber;
import java.util.List;

public class LottoUtils {
    private LottoUtils() {}

    public static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
