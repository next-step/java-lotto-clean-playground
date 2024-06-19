package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoPriceTest {

    @Test
    @DisplayName("로또 당첨금 묶음 테스트")
    void getLottoPriceBundle() {
        // given
        final List<Integer> expected = List.of(5000, 50000, 1500000, 30000000, 2000000000);

        // when
        final List<Integer> actual = LottoPrice.getLottoPriceBundle();

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("일치하는 개수에 따라 로또 가격을 반환해주는 테스트 - 보너스 넘버인 경우")
    void getLottoPrice_contain_bonus_number() {
        // given
        final String sameLottoNumber = "5BONUS";
        final int expected = 30000000;

        // when
        final int actual = LottoPrice.getLottoPrice(sameLottoNumber);

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("일치하는 개수에 따라 로또 가격을 반환해주는 테스트 - 보너스 넘버가 아닌 경우")
    void getLottoPrice_not_contain_bonus_number() {
        // given
        final String sameLottoNumber = "5";
        final int expected = 1500000;

        // when
        final int actual = LottoPrice.getLottoPrice(sameLottoNumber);

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("로또 맞은 개수 묶음 테스트")
    void getSameLottoNumberBundle() {
        // given
        final List<String> expected = List.of("3", "4", "5", "5BONUS", "6");

        // when
        final List<String> actual = LottoPrice.getSameLottoNumberBundle();

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("5BONUS 반환 테스트")
    void getBonusSameLottoNumber() {
        // given
        final String expected = "5BONUS";

        // when
        final String actual = LottoPrice.getBonusSameLottoNumber();

        // then
        assertEquals(expected, actual);
    }
}
