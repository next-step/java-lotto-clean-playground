import domain.Lotto;
import domain.LottoMakeStrategy;
import domain.LottosCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("로또 생성 및 저장 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottosCreatorTest {

    @Test
    @DisplayName("로또를 생성하고 저장할 수 있다.")
    public void createAndSaveLottos() {
        //given
        final List<Integer> lottoNumber = List.of(5, 14, 16, 35, 38, 44);
        final Lotto lotto = new Lotto(lottoNumber);
        final int lottoCount = 1;

        LottoMakeStrategy lottoMakeStrategy = new TestLottoMakeStrategy();
        LottosCreator lottosCreator = new LottosCreator(lottoMakeStrategy);

        List<Lotto> expected = new ArrayList<Lotto>(List.of(lotto));

        //when
        List<Lotto> result = lottosCreator.createLottos(lottoCount);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
