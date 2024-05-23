package modelTest;

import model.Consumer;
import model.Lotto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConsumerTest {

    private Consumer consumer = new Consumer("5000", "2");

    private List<Lotto> autoLottos;
    private List<Lotto> directLottos;

    public ConsumerTest() {
        makeAutoLottos();
        makeDirectLottos();
    }

    private void makeAutoLottos() {

        autoLottos = new ArrayList<>();

        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 42, 43, 44));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        autoLottos.add(lotto1);
        autoLottos.add(lotto2);
        autoLottos.add(lotto3);
    }

    private void makeDirectLottos() {

        directLottos = new ArrayList<>();

        Lotto lotto1 = new Lotto(List.of(8, 21, 23, 41, 42, 43));
        Lotto lotto2 = new Lotto(List.of(3, 5, 11, 16, 32, 38));

        directLottos.add(lotto1);
        directLottos.add(lotto2);
    }

    @Test
    void 소비자가_로또를_구매한다() {

        consumer.BuyLottos(autoLottos, directLottos);

        List<Lotto> expectedDirect = directLottos;
        List<Lotto> expectedAuto = autoLottos;
        List<Lotto> actualDirect = consumer.getHaveLottos().subList(0, 2);
        List<Lotto> actualAuto = consumer.getHaveLottos().subList(2, 5);


        assertThat(actualDirect)
                .containsExactlyElementsOf(expectedDirect);
        assertThat(actualAuto)
                .containsExactlyElementsOf(expectedAuto);
    }

    @Test
    void 소비자가_모든_로또를_정답과_비교한다() {

        consumer.BuyLottos(autoLottos, directLottos);
        consumer.analizeAllLottos(List.of(1, 2, 3, 4, 5, 6), 7);

        List<Integer> actualMatchedNumber = List.of(0, 2, 6, 3, 5);
        List<Integer> expectedMatchedNumber = new ArrayList<>();

        for (Lotto lotto : consumer.getHaveLottos()) {
            expectedMatchedNumber.add(lotto.getCollectedCount());
        }

        assertThat(actualMatchedNumber)
                .containsExactlyElementsOf(expectedMatchedNumber);
    }
}
