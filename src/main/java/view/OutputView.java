package view;

import java.util.List;

public class OutputView {

    public void printLotto(List<LottoResult> lottos) {
        printLinebreak();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.stream()
                .map(LottoResult::numbers)
                .forEach(System.out::println);
    }

    private void printLinebreak() {
        System.out.print(System.lineSeparator());
    }
}
