package view.output;

import java.util.List;

public class ConsoleOutputView implements OutputView {

    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_SUFFIX = "]";
    private static final String LOTTO_JOIN_MARK = ", ";

    @Override
    public void printAskInputLottoMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void printLottoSize(final int size) {
        System.out.println(size + "개를 구매했습니다.");
        System.out.println();
    }

    @Override
    public void printLottoNumbers(final List<Integer> numbers) {
        List<String> numberValues = numbers.stream()
                .map(Object::toString)
                .toList();

        System.out.println(LOTTO_PREFIX + String.join(LOTTO_JOIN_MARK, numberValues) + LOTTO_SUFFIX);
    }
}
