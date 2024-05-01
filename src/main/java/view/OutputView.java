package view;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    public void outputLottoSet(int lottoCount, List<Set<Integer>> lottoNumberSet) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(lottoCount + "개 구매했습니다.");

        lottoNumberSet.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

    }
}
