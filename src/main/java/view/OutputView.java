package view;


import java.util.List;

public class OutputView {

    private int i;
    private final int INITIAL_NUM = 0;

    public void repeatLotto(int num, List<Integer> lottoNumbers) {
        for (i = INITIAL_NUM; i <= num; i++) {
            System.out.println(lottoNumbers);
        }
    }
}
