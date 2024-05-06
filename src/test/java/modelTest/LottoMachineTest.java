package modelTest;

import model.Lotto;
import model.LottoMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로또 기계 Test")
class LottoMachineTest {

    private LottoMachine lottoMachine = new LottoMachine();
    private List<String> directNumberTexts;

    public LottoMachineTest() {
        makeDirectNumberText();
    }

    private void makeDirectNumberText() {

        directNumberTexts = List.of(
                "8, 21, 23, 41, 42, 43",
                "3, 5, 11, 16, 32, 38",
                "7, 11, 16, 35, 36, 44"
        );
    }

    @Test
    void 수동_로또_번호가_생성된다() {

        lottoMachine.makeDirectNumber(directNumberTexts);

        List<Lotto> expected = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44))
        );

        List<Lotto> actual = lottoMachine.getDirectNumbers();

        IntStream.range(0, actual.size())
                .forEach(i -> assertThat(actual.get(i).getLottoNumber())
                        .containsExactlyElementsOf(expected.get(i).getLottoNumber()));

    }

    @Test
    void 자동_로또_번호가_생성된다() {
        /*
         * 이 부분이 제일 문제입니다.
         * 로또가 랜던값으로 자동으로 만들어져서
         * 테스트 코드를 만들지 못하고 있습니다.
         * 검색해 봤더니 mokito를 이용하면 편리할 수 있다는데
         * 이 문제에서 요구하는것은 아닌거 같습니다.
         * 피드백 받고 싶습니다.*/
    }
}
