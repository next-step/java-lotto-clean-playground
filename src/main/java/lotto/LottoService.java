package lotto;

import java.util.Collection;
import java.util.Scanner;

public class LottoService {
    public void execute() {
        int payment = getPayment();
        LottoShop lottoShop = new LottoShop();
        Collection<Lotto> lottos = lottoShop.buyRandom(payment);
        printLottos(lottos);
    }

    private int getPayment() {
        System.out.println("구입금액을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        int payment = scanner.nextInt();
        scanner.close();
        return payment;
    }

    private void printLottos(Collection<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}
