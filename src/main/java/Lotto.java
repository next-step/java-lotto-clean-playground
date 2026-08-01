import java.util.*;

public class Lotto {
    private int calculateCount(int price) {
        return price / 1000;
    }

    private static List<Integer> lottoList() {
        List<Integer> lotto = new ArrayList<>();

        for (int i = 0; i < 45; i++) {
            lotto.add(i+1);
        }
        return lotto;
    }

    private void lottoShuffle(List<Integer> lotto) {
        Collections.shuffle(lotto);
    }

    private List<Integer> lottoPick(List<Integer> lotto) {
        List<Integer> lottoSix = new ArrayList<>(lotto.subList(0, 6));
        return lottoSix;
    }

    private void lottoSort(List<Integer> lotto) {
        Collections.sort(lotto);
    }

    private void run() {
        List<Integer> lottoList = lottoList();

        lottoShuffle(lottoList);
        lottoList = lottoPick(lottoList);
        lottoSort(lottoList);

        System.out.println(lottoList);
    }

    private int inputPrice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("구입금액을 입력해 주세요.");
        int price = scanner.nextInt();

        return price;
    }

    public static void main(String[] args) {
        Lotto lotto = new Lotto();
        int price = lotto.calculateCount(lotto.inputPrice());

        System.out.println(price + "개를 구매했습니다.");
        for (int i = 0; i < price; i++) {
            lotto.run();
        }
    }
}
