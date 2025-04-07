package util;

import domain.LottoNumber;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {

  public static List<LottoNumber> parseLottoNumbers(String input) {
    return Arrays.stream(input.split(", "))
        .map(Integer::parseInt)
        .map(LottoNumber::new)
        .collect(Collectors.toList());
  }
}
