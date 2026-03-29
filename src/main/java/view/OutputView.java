package view;

import domain.Lotto;
import domain.Lottos;

import java.util.List;

public class OutputView {

    public void printResultHeader(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<List<Integer>> lottoNumbers) {
        for (List<Integer> numbers : lottoNumbers) {
            System.out.println(numbers);
        }
    }
}
