package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {

    @Test
    @DisplayName("Lotto 객체는 LottoNumber 6개로 이루어진다.")
    public void 로또_객체는_6자리이다() {
        int LOTTO_INSTANCE_SIZE = 6;
        LottoMaker lottoMaker = new LottoMaker();
        Lotto lotto = lottoMaker.autoMake(1).get(0);

        assertEquals(LOTTO_INSTANCE_SIZE, lotto.lottoNumbers().size());
    }

    @Test
    @DisplayName("Lotto 객체는 중복된 숫자 6자리를 받지 않는다.")
    public void 로또_객체는_중복된_숫자를_갖지_않는다() {


        assertThrows(IllegalArgumentException.class, this::causeException);

    }

    private void causeException() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(1));

        Lotto lotto = new Lotto(lottoNumbers);
    }
}