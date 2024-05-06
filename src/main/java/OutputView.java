import java.util.List;

public class OutputView {

    public void printLotto(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.stream().map(Lotto::numbers).forEach(System.out::println);
    }

}
