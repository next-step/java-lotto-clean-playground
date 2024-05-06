package Model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static Model.Constants.LOTTO_PRICE;

public class LottoTest {

    @Test
    void 로또가_잘_생성_돼야함(){
        int[] lottos = new int[]{1,2,3,4,5,6};
        ArrayList<LottoNumber> lottoNumbers = (ArrayList<LottoNumber>) Arrays.stream(lottos).mapToObj(LottoNumber::new).collect(Collectors.toList());
        Lotto lotto = new Lotto(new LottoPrice(LOTTO_PRICE),lottoNumbers);

        Assertions.assertThat(lotto.getLottoNumbers()).isEqualTo(lottoNumbers);
    }

    @Test
    void 로또가_가격이_잘_맞아야함(){
        int[] lottos = new int[]{1,2,3,4,5,6};
        ArrayList<LottoNumber> lottoNumbers = (ArrayList<LottoNumber>) Arrays.stream(lottos).mapToObj(LottoNumber::new).collect(Collectors.toList());
        Lotto lotto = new Lotto(new LottoPrice(LOTTO_PRICE),lottoNumbers);

        Assertions.assertThat(lotto.getPrice()).isEqualTo(LOTTO_PRICE);
    }
}
