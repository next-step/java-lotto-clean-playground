package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("model.Rank 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RankTest {
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, NONE",
            "0, true, NONE"
    })
    void 일치_개수에_맞는_Rank를_반환한다(int matchCount, boolean matchBonus, Rank expectedRank) {
        //when
        Rank actualRank = Rank.of(matchCount, matchBonus);

        //then
        assertEquals(expectedRank, actualRank);
    }
}
