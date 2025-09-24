package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumbersRepositoryTest {

    @Test
    void 저장소에_로또번호를_추가하면_읽을때_사이즈가_증가한다() {
        // given
        LottoNumbersRepository repository = new LottoNumbersRepository();
        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));

        // when
        repository.addLottoNumbers(lottoNumbers);

        // then
        assertEquals(1, repository.readLottoNumbersRepository().size());
    }

    @Test
    void 반환된_리스트는_불변리스트이다() {
        // given
        LottoNumbersRepository repository = new LottoNumbersRepository();
        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        repository.addLottoNumbers(lottoNumbers);

        // when
        List<LottoNumbers> list = repository.readLottoNumbersRepository();

        // then
        assertThrows(UnsupportedOperationException.class,
                () -> list.add(lottoNumbers));
    }
}
