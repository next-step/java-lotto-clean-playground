package domain;

import model.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RatingTest {

    @Test
    void 다섯개를_맞춘_로또가_보너스볼도_맞추면_SECOND를_반환한다() {

        Rating rating = Rating.getRating(5, true);

        assertEquals(Rating.SECOND, rating);
    }

    @Test
    void 다섯개를_맞춘_로또가_보너스볼을_못맞추면_THIRD를_반환한다() {

        Rating rating = Rating.getRating(5, false);

        assertEquals(Rating.THIRD, rating);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 로또_맞은_갯수가_3개이하면_MISS를_반환한다(int matchCount) {

        Rating rating = Rating.getRating(matchCount, false);

        assertEquals(Rating.MISS, rating);
    }
}
