package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.RepeatedTest;

@SuppressWarnings("NonAsciiCharacters")
class LottoMakerTest {
    LottoMaker lottoMaker = new LottoMaker();

    @RepeatedTest(10)
    void 생성된_로또는_6개이며_정렬되어_있다() {
        // LottoNumbers가 정렬에 문제가 있다면 오류를 던질 것
        assertThatCode(() -> lottoMaker.makeLottoNumbers())
                .doesNotThrowAnyException();
    }
}
