package lotto.util;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;

import java.util.Arrays;
import lotto.domain.Lottos;
import lotto.domain.Payment;

public class InputParser {

  private static final String DELIMITER = ",";

  private InputParser() {
  }

  public static Payment parsePayment(final Long input) {
    return new Payment(input);
  }

  public static LottoResult parseLottoResult(final String input) {
    validateInput(input);

    return LottoResult.of(parseNumbers(input));
  }

  public static Lotto parseLotto(final String input) {
    validateInput(input);

    return Lotto.of(parseNumbers(input));
  }

  private static List<Integer> parseNumbers(final String input) {
    return Arrays.stream(input.split(DELIMITER))
        .map(String::trim)
        .mapToInt(Integer::parseInt)
        .boxed()
        .sorted()
        .toList();
  }

  public static Lottos parseLottos(final List<String> inputs) {
    List<Lotto> lottoList = new ArrayList<>();

    for (String input : inputs) {
      lottoList.add(parseLotto(input));
    }

    return new Lottos(lottoList);
  }

  private static void validateInput(final String input) {
    if (input == null || input.trim().isEmpty()) {
      throw new RuntimeException("입력값이 비어있습니다.");
    }
  }
}
