package Model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoValidaterTest {

    @Test
    void 로또_리스트에_중복이_있으면_안됨(){
        int[] lottos = new int[]{1,2,3,5,5,6};
        ArrayList<LottoNumber> lottoNumbers = (ArrayList<LottoNumber>) Arrays.stream(lottos).mapToObj(LottoNumber::new).collect(Collectors.toList());

        Assertions.assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(new LottoPrice(Constants.LOTTO_PRICE),lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_리스트가_6개여야만_한다(){
        int[] lottos = new int[]{1,2,3,5,6};
        ArrayList<LottoNumber> lottoNumbers = (ArrayList<LottoNumber>) Arrays.stream(lottos).mapToObj(LottoNumber::new).collect(Collectors.toList());

        Assertions.assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(new LottoPrice(Constants.LOTTO_PRICE),lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
