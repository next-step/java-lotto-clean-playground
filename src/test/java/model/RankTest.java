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
    @CsvSource({"6, FIRST", "5, SECOND", "4, THIRD", "3, FOURTH", "2, NONE", "0, NONE"})
    void 일치_개수에_맞는_Rank를_반환한다(int matchCount, Rank expectedRank) {
        Rank actualRank = Rank.of(matchCount);

        assertEquals(expectedRank, actualRank);
    }
}
