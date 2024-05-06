package domain;

import model.Rating;
import model.RatingInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingInfoTest {

    RatingInfo ratings = new RatingInfo();

    @Test
    void ratings를_처음으로_생성하면_모든_키의값들은_0이다() {

        for (Rating rating : Rating.values()) {
            assertEquals(0, ratings.getCount(rating));
        }
    }

    @Test
    void rating의_update함수를_실행하면_키의값은_1증가한다() {

        for (Rating rating : Rating.values()) {
            ratings.update(rating);
            assertEquals(1, ratings.getCount(rating));
        }
    }
}
