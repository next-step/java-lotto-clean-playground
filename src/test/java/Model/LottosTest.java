package Model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LottosTest {

    @Test
    void 매칭_결과를_잘_리턴해줘야함(){
        int[] lotto1 = new int[]{1,2,3,4,5,6};
        ArrayList<LottoNumber> lottoNumbers1 = (ArrayList<LottoNumber>) Arrays.stream(lotto1).mapToObj(LottoNumber::new).collect(Collectors.toList());
        Lotto lotto11 = new Lotto(new LottoPrice(Constants.LOTTO_PRICE),lottoNumbers1);

        int[] lotto2 = new int[]{4,5,6,7,8,9};
        ArrayList<LottoNumber> lottoNumbers2 = (ArrayList<LottoNumber>) Arrays.stream(lotto2).mapToObj(LottoNumber::new).collect(Collectors.toList());
        Lotto lotto22 = new Lotto(new LottoPrice(Constants.LOTTO_PRICE),lottoNumbers2);

        int[] lotto3 = new int[]{1,2,5,6,10,11};
        ArrayList<LottoNumber> lottoNumbers3 = (ArrayList<LottoNumber>) Arrays.stream(lotto3).mapToObj(LottoNumber::new).collect(Collectors.toList());
        Lotto lotto33 = new Lotto(new LottoPrice(Constants.LOTTO_PRICE),lottoNumbers3);

        Lottos lottos = new Lottos();
        lottos.addNewLotto(lotto11);
        lottos.addNewLotto(lotto22);
        lottos.addNewLotto(lotto33);

        int[] lotto4 = new int[]{1,2,5,15,16,17};
        ArrayList<LottoNumber> winnerLotto1 = (ArrayList<LottoNumber>) Arrays.stream(lotto4).mapToObj(LottoNumber::new).collect(Collectors.toList());

        Lotto winnerLottos = new Lotto(new LottoPrice(Constants.LOTTO_PRICE),winnerLotto1);
        Assertions.assertThat(lottos.matchList(winnerLottos,10))
                .isEqualTo(new int[]{2,0,0,0,0});
    }
}
