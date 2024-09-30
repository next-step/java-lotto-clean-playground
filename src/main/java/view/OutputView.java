package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;

import java.util.List;

public class OutputView {

    public OutputView() {
    }

    public void printPurchasedCount(int autoCount, int manualCount) {
        if (manualCount > 0 && autoCount > 0) {
            System.out.print("수동으로 " + manualCount + "장, " + "자동으로 " + autoCount + "개를");
        }

        else if (manualCount > 0) {
            System.out.print("수동으로 " + manualCount + "장을");
        }

        else if (autoCount > 0) {
            System.out.print("자동으로 " + autoCount + "개를");
        }

        System.out.println(" 구매했습니다.");
    }

    public void printLottosNumbers(List<Lotto> lottos) {
//        lottos.stream()
//                .map(Lotto::getLottoNumbers)
//                .map(LottoNumbers::getNumbers)
//                .forEach(
//                        list -> {
//                            System.out.print("[");
//                            list.forEach(
//                                    l ->  {
//                                        System.out.print(l.getValue() + ", ");
//                                    });
//                            System.out.print("]\n");
//                        });


        List<List<LottoNumber>> flatLottosNumbers = lottos.stream()
                .map(Lotto::getLottoNumbers)
                .map(LottoNumbers::getNumbers).toList();

        int length = flatLottosNumbers.size();

        for (int i = 0; i < length; i++) {
            System.out.print("[");
            for (int j = 0; j < LottoNumbers.SIZE; j++) {
                System.out.print(flatLottosNumbers.get(i).get(j).getValue());

                if (j != LottoNumbers.SIZE - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]\n");
        }
    }
}
